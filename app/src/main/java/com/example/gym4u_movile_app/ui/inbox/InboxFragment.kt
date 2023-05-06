package com.example.gym4u_movile_app.ui.inbox

import android.os.Bundle
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
import com.example.gym4u_movile_app.util.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InboxFragment : Fragment() {


    private lateinit var binding: FragmentInboxBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInboxBinding.inflate(inflater, container, false)
        loadMessages()
        return binding.root
    }


    private fun loadMessages() {
        RetrofitBuilder.build()
            .create(FollowerService::class.java)
            .getFollowers(2)
            .enqueue(object : Callback<BaseResponse<Follower>> {
                override fun onResponse(
                    call: Call<BaseResponse<Follower>>,
                    response: Response<BaseResponse<Follower>>
                ) {
                    if(response.isSuccessful) {
                        binding.rvChats.layoutManager = LinearLayoutManager(context)
                        binding.rvChats.adapter = InboxAdapter(response.body()!!.content)
                    }

                }

                override fun onFailure(call: Call<BaseResponse<Follower>>, t: Throwable) {
                    Log.d("inboxFragment", t.toString())
                }

            })
    }

}