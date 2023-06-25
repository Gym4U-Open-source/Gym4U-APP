package com.example.gym4u_movile_app.ui.posts

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.gym4u_movile_app.entities.Follower
import com.example.gym4u_movile_app.entities.Post
import com.example.gym4u_movile_app.entities.Profile
import com.example.gym4u_movile_app.entities.Role
import com.example.gym4u_movile_app.entities.User
import com.example.gym4u_movile_app.services.PostService
import com.example.gym4u_movile_app.util.RetrofitBuilder
import com.example.gym4u_movile_app.util.UtilFn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding get() = _binding!!
    private val filteredPosts = ArrayList<Post>()

    var posts = ArrayList<Post>()
    val postsAdapter = PostsAdapter(filteredPosts)

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

        binding.etFilter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                search(s?.toString())
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun search(keyword: String?) {
        filteredPosts.clear()
        filteredPosts.addAll(posts)
        if(keyword != null)
            filteredPosts.removeIf { post -> !UtilFn.textContainAnyCase(
                post.description,
                keyword
            ) && !UtilFn.textContainAnyCase(post.description, keyword)
            }

        binding.rvPosts.adapter?.notifyDataSetChanged()
    }

    private fun loadPosts() {

        val retrofit = RetrofitBuilder.build()

        val postService: PostService = retrofit.create(PostService::class.java)

        val request = postService.getAll()

        request.enqueue(object: Callback<BaseResponse<Post>> {
            override fun onResponse(
                call: Call<BaseResponse<Post>>,
                response: Response<BaseResponse<Post>>
            ) {
                response.body()!!.content.let {
                    addPosts(it)
                }

                //response.body()!!.content.forEach {
                //    posts.add(it)
                //}

                //postsAdapter.notifyDataSetChanged()
                //Log.d("Post: ", posts[0].id.toString())
            }

            override fun onFailure(call: Call<BaseResponse<Post>>, t: Throwable) {
                Log.d("postAtivity", t.toString())
            }
        })
    }

    private fun addPosts(posts: List<Post>) {
        filteredPosts.addAll(posts)
        this.posts.addAll(posts)
        binding.rvPosts.adapter?.notifyItemRangeInserted(0, posts.size)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}