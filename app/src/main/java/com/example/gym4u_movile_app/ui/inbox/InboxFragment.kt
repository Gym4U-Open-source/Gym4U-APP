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
import com.example.gym4u_movile_app.services.FollowerService
import com.example.gym4u_movile_app.util.AppPreferences.Companion.preferences
import com.example.gym4u_movile_app.util.RetrofitBuilder
import com.example.gym4u_movile_app.util.UtilFn.Companion.isCoach
import com.example.gym4u_movile_app.util.UtilFn.Companion.textContainAnyCase
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InboxFragment : Fragment() {


    private lateinit var binding: FragmentInboxBinding
    private val followers = ArrayList<Follower>()
    private val filteredFollowers = ArrayList<Follower>()
    private val followerService = RetrofitBuilder.build().create(FollowerService::class.java)
    private var isCoach: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInboxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFollowers()
        loadViews()
    }

    private fun loadViews() {
        binding.rvChats.layoutManager = LinearLayoutManager(context)
        binding.rvChats.adapter = FollowersAdapter(filteredFollowers, isCoach)

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

    private fun addFollowers(followers: List<Follower>) {
        filteredFollowers.addAll(followers)
        this.followers.addAll(followers)
        binding.rvChats.adapter?.notifyItemRangeInserted(0, followers.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun search(keyword: String?) {
        filteredFollowers.clear()
        filteredFollowers.addAll(followers)
        if(keyword != null)
            filteredFollowers.removeIf { follower -> !follower.clientUser.username.textContainAnyCase(keyword) && !follower.clientUser.email.textContainAnyCase(keyword) }

        binding.rvChats.adapter?.notifyDataSetChanged()
    }

    private fun loadFollowers() {
        val user = preferences.getUser()
        isCoach = user.isCoach()
        if(isCoach)
            followerService
                .getFollowers(user.id.toLong())
                .enqueue(object : Callback<BaseResponse<Follower>> {
                    override fun onResponse(
                        call: Call<BaseResponse<Follower>>,
                        response: Response<BaseResponse<Follower>>
                    ) {
                        if(response.isSuccessful)
                            response.body()?.content?.let { addFollowers(it) }

                    }

                    override fun onFailure(call: Call<BaseResponse<Follower>>, t: Throwable) {
                        Log.d("inboxFragment", t.toString())
                    }

                })
        else
            followerService
                .getFollowersSwitch(0, user.id.toLong())
                .enqueue(object : Callback<Follower> {
                    override fun onResponse(call: Call<Follower>, response: Response<Follower>) {
                        if(response.isSuccessful)
                            addFollowers(listOf(response.body()!!))
                        else if(response.code() == 302)
                            addFollowers(listOf(Gson().fromJson(response.errorBody()?.string(), Follower::class.java)))
                    }

                    override fun onFailure(call: Call<Follower>, t: Throwable) {
                        Log.d("inboxFragment", t.toString())
                    }

                })
    }

}