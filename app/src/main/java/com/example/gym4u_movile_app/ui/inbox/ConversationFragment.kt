package com.example.gym4u_movile_app.ui.inbox

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.gym4u_movile_app.databinding.FragmentConversationBinding
import com.example.gym4u_movile_app.entities.Conversation
import com.example.gym4u_movile_app.services.ConversationService
import com.example.gym4u_movile_app.util.RetrofitBuilder
import com.example.gym4u_movile_app.util.UtilFn.Companion.toUTF8String
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConversationFragment : Fragment() {
    private lateinit var binding: FragmentConversationBinding
    private val args: ConversationFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConversationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        args.coachFollower
        val username = toUTF8String(args.coachFollower.clientUser.username)
        binding.tvConversationChatName.text = username
        binding.tvConversationChatAvatar.text = username[0].uppercase()
        RetrofitBuilder.build()
            .create(ConversationService::class.java)
            .getConversation(args.coachFollower.coachUser.id, args.coachFollower.clientUser.id)
            .enqueue(object : Callback<Conversation> {
                override fun onResponse(
                    call: Call<Conversation>,
                    response: Response<Conversation>
                ) {
                    if(response.isSuccessful)
                        Log.d("conversationFragment", response.body()!!.messages.toString())
                    else Log.d("conversationFragment", response.body().toString())


                }

                override fun onFailure(call: Call<Conversation>, t: Throwable) {
                    Log.d("conversationFragment", t.toString())
                }

            })
    }


}