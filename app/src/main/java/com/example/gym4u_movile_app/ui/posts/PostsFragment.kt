package com.example.gym4u_movile_app.ui.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gym4u_movile_app.PostsAdapter
import com.example.gym4u_movile_app.databinding.FragmentPostsBinding
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Comment
import com.example.gym4u_movile_app.entities.Post
import com.example.gym4u_movile_app.entities.Profile
import com.example.gym4u_movile_app.entities.Role
import com.example.gym4u_movile_app.entities.User
import com.example.gym4u_movile_app.services.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!

    var posts = ArrayList<Post>()
    val postsAdapter = PostsAdapter(posts)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val postsViewModel =
            ViewModelProvider(this).get(PostsViewModel::class.java)

        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loadPosts()
        initView()

        return root
    }

    private fun initView() {
        val rvPosts = binding.rvPosts

        // Configurar el LayoutManager
        val layoutManager = LinearLayoutManager(context)
        rvPosts.layoutManager = layoutManager

        // Configurar el adaptador
        rvPosts.adapter = postsAdapter
    }

    private fun loadPosts() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gym4u-api-388317.rj.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val postService: PostService = retrofit.create(PostService::class.java)

        val request = postService.getAll()

        request.enqueue(object: Callback<BaseResponse<Post>> {
            override fun onResponse(
                call: Call<BaseResponse<Post>>,
                response: Response<BaseResponse<Post>>
            ) {
                response.body()!!.content.forEach {
                    posts.add(it)
                }

                postsAdapter.notifyDataSetChanged()
                Log.d("Post: ", posts[0].id.toString())
            }

            override fun onFailure(call: Call<BaseResponse<Post>>, t: Throwable) {
                Log.d("postAtivity", t.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}