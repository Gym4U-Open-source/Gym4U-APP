package com.example.gym4u_movile_app.ui.inbox

import FollowersAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gym4u_movile_app.databinding.FragmentInboxBinding
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Follower
import com.example.gym4u_movile_app.entities.FollowerUser
import com.example.gym4u_movile_app.services.FollowerService
import com.example.gym4u_movile_app.util.RetrofitBuilder
import com.example.gym4u_movile_app.util.UtilFn.Companion.textContainAnyCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InboxFragment : Fragment() {


    private lateinit var binding: FragmentInboxBinding
    private val followers = ArrayList<Follower>()
    private val filteredFollowers = ArrayList<Follower>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInboxBinding.inflate(inflater, container, false)
        loadViews()
        loadFollowers()
        return binding.root
    }

    private fun loadViews() {
        binding.rvChats.layoutManager = LinearLayoutManager(context)
        binding.rvChats.adapter = FollowersAdapter(filteredFollowers)

        binding.etChatFilter.addTextChangedListener(object : TextWatcher {
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
        filteredFollowers.clear()
        filteredFollowers.addAll(followers)
        if(keyword != null)
            filteredFollowers.removeIf { follower -> !textContainAnyCase(follower.clientUser.username, keyword) && !textContainAnyCase(follower.clientUser.email, keyword) }

        binding.rvChats.adapter?.notifyDataSetChanged()
    }

    private fun loadFollowers() {
        RetrofitBuilder.build()
            .create(FollowerService::class.java)
            .getFollowers(2)
            .enqueue(object : Callback<BaseResponse<Follower>> {
                override fun onResponse(
                    call: Call<BaseResponse<Follower>>,
                    response: Response<BaseResponse<Follower>>
                ) {
                    if(response.isSuccessful) {
                        response.body()?.content?.let {
                            filteredFollowers.addAll(it)
                            followers.addAll(it)
                            binding.rvChats.adapter?.notifyItemRangeInserted(0, followers.size)
                        }
                    }

                }

                override fun onFailure(call: Call<BaseResponse<Follower>>, t: Throwable) {
                    Log.d("inboxFragment", t.toString())
                }

            })
    }

}