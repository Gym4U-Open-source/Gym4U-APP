package com.example.gym4u_movile_app

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gym4u_movile_app.databinding.FragmentCommunityBinding
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Follower
import com.example.gym4u_movile_app.entities.Post
import com.example.gym4u_movile_app.entities.resources.CreateCommentResource
import com.example.gym4u_movile_app.entities.resources.CreatePostResource
import com.example.gym4u_movile_app.entities.resources.UserResource
import com.example.gym4u_movile_app.enums.Roles
import com.example.gym4u_movile_app.services.CommentService
import com.example.gym4u_movile_app.services.FollowerService
import com.example.gym4u_movile_app.services.PostService
import com.example.gym4u_movile_app.util.AppPreferences
import com.example.gym4u_movile_app.util.RetrofitBuilder
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityFragment : Fragment() {
    private var _binding: FragmentCommunityBinding? = null
    private val binding get() = _binding!!

    var posts = ArrayList<Post>()
    private val filteredPosts = ArrayList<Post>()
    private lateinit var preferences: AppPreferences

    val postsAdapter = PostsAdapter(filteredPosts)
    private val followerService = RetrofitBuilder.build().create(FollowerService::class.java)
    private val followers = ArrayList<Follower>()
    private val filteredFollowers = ArrayList<Follower>()

    private var isCoach: Boolean = false

    private val postService = RetrofitBuilder.build().create(PostService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommunityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        preferences = AppPreferences(requireContext())

        loadFollowers()
        initView()

        return root
    }

    private fun initView() {
        val user = preferences.getUser()
        user.roles.forEach {
            if(it == Roles.COACH.name)
                isCoach = true
        }

        val btNewPostCommunity: Button = binding.btNewPostCommunity

        if (isCoach) {
            btNewPostCommunity.text = "Publicar"

            btNewPostCommunity.setOnClickListener {
                showCreatePostModal()
            }

        } else {
            btNewPostCommunity.text = "Siguiendo"
        }

        val rvPosts = binding.rvPostsCommunity

        // Configurar el LayoutManager
        val layoutManager = LinearLayoutManager(context)
        rvPosts.layoutManager = layoutManager

        // Configurar el adaptador
        rvPosts.adapter = postsAdapter
    }

    private fun showCreatePostModal() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.create_post_modal, null)

        val etComment = dialogView.findViewById<EditText>(R.id.etTitleModalPost)
        val etDescriptionModalPost = dialogView.findViewById<EditText>(R.id.etDescriptionModalPost)
        val etImageUrlModalPost = dialogView.findViewById<EditText>(R.id.etImageUrlModalPost)

        val btCreatePost = dialogView.findViewById<Button>(R.id.btCreatePost)

        val dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)

        val dialog = dialogBuilder.create()

        val window = dialog.window
        val layoutParams = window?.attributes
        layoutParams?.gravity = Gravity.CENTER
        window?.attributes = layoutParams

        window?.setBackgroundDrawableResource(R.drawable.post_modal_rounded)

        btCreatePost.setOnClickListener {
            val title = etComment.text.toString().trim()
            val description = etDescriptionModalPost.text.toString().trim()
            val imageUrl = etImageUrlModalPost.text.toString().trim()

            if (title.isEmpty() || description.isEmpty() || imageUrl.isEmpty()) {
                Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Todos los campos están llenos, realizar la acción correspondiente aquí
                // Por ejemplo, llamar a una función para crear el post con los datos proporcionados
                //createPost(title, description, imageUrl)
                val user = preferences.getUser()

                val userResource = UserResource(user.id.toLong())
                val createPostResource = CreatePostResource(title, description, imageUrl, userResource)

                user.token?.let {
                    postService
                        .createComment("Bearer " + it, createPostResource)
                        .enqueue(object : Callback<Post> {
                            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                                if (response.isSuccessful) {
                                    val newPost = response.body() // Obtener el comentario creado
                                    if (newPost != null) {
                                        //commentsAdapter.addComment(newComment) // Agregar el comentario a la lista
                                        // Actualizar la vista del RecyclerView si es necesario
                                        //rvComments.scrollToPosition(commentsAdapter.itemCount - 1)
                                        filteredPosts.add(newPost)
                                        postsAdapter.notifyDataSetChanged()
                                        binding.rvPostsCommunity.scrollToPosition(postsAdapter.itemCount - 1)
                                        //rvPo.scrollToPosition(commentsAdapter.itemCount - 1)
                                    }

                                    Toast.makeText(context, "EXITO", Toast.LENGTH_SHORT).show()
                                } else {
                                    // Error al crear el comentario, manejar el error de acuerdo a tus necesidades
                                    Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<Post>, t: Throwable) {
                                TODO("Not yet implemented")
                            }

                        })
                }


                dialog.dismiss() // Cerrar el diálogo después de crear el post
            }
        }

        dialog.show()
    }

    private fun loadPosts(coachId: Long) {
        posts.clear()
        filteredPosts.clear()

        val user = preferences.getUser()
        user.roles.forEach {
            if(it == Roles.COACH.name)
                isCoach = true
        }

        val retrofit = RetrofitBuilder.build()

        val postService: PostService = retrofit.create(PostService::class.java)

        val request = postService.getAll()

        request.enqueue(object: Callback<BaseResponse<Post>> {
            override fun onResponse(
                call: Call<BaseResponse<Post>>,
                response: Response<BaseResponse<Post>>
            ) {
                response.body()!!.content.let {
                    addPosts(it, coachId)
                }
            }

            override fun onFailure(call: Call<BaseResponse<Post>>, t: Throwable) {
                Log.d("postAtivity", t.toString())
            }
        })
    }

    private fun loadFollowers() {
        val user = preferences.getUser()
        user.roles.forEach {
            if(it == Roles.COACH.name)
                isCoach = true
        }

        if(isCoach) {
            binding.tvusernameCommunity.text = user.username
            binding.tvEmailCommunity.text = user.email

            getFollowersByCoachId(user.id.toLong())
        }
        else
            getClientFollow(user.id.toLong())
    }

    private fun getClientFollow(clientId: Long) {
        followerService
            .getFollowersSwitch(0, clientId)
            .enqueue(object : Callback<Follower> {
                override fun onResponse(call: Call<Follower>, response: Response<Follower>) {
                    if(response.isSuccessful) {
                        getFollowersByCoachId(response.body()!!.coachUser.id)

                        binding.tvusernameCommunity.text = response.body()!!.coachUser.username
                        binding.tvEmailCommunity.text = response.body()!!.coachUser.email
                    }
                    else if(response.code() == 302) {
                        val follow = Gson().fromJson(
                            response.errorBody()?.string(),
                            Follower::class.java
                        )

                        getFollowersByCoachId(follow.coachUser.id)

                        binding.tvusernameCommunity.text = follow.coachUser.username
                        binding.tvEmailCommunity.text = follow.coachUser.email
                    }
                }

                override fun onFailure(call: Call<Follower>, t: Throwable) {
                    Log.d("inboxFragment", t.toString())
                }
            })
    }

    private fun getFollowersByCoachId(coachId: Long) {
        followerService
            .getFollowers(coachId)
            .enqueue(object : Callback<BaseResponse<Follower>> {
                override fun onResponse(
                    call: Call<BaseResponse<Follower>>,
                    response: Response<BaseResponse<Follower>>
                ) {
                    if(response.isSuccessful) {
                        response.body()?.content?.let { addFollowers(it) }
                        binding.tvMembersCommunity.text = "${followers.size} miembros"

                        loadPosts(coachId)
                    }

                }

                override fun onFailure(call: Call<BaseResponse<Follower>>, t: Throwable) {
                    Log.d("inboxFragment", t.toString())
                }

            })
    }

    private fun addFollowers(followers: List<Follower>) {
        filteredFollowers.addAll(followers)
        this.followers.addAll(followers)
        //binding.rvChats.adapter?.notifyItemRangeInserted(0, followers.size)
    }

    private fun addPosts(posts: List<Post>, coachId: Long) {
        val postsToAdd = posts.filter { it.user?.id!!.toLong() == coachId }
        filteredPosts.addAll(postsToAdd)
        binding.rvPostsCommunity.adapter?.notifyItemRangeInserted(0, filteredPosts.size)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}