package com.example.gym4u_movile_app.ui.inbox

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.databinding.FragmentConversationBinding
import com.example.gym4u_movile_app.models.Conversation
import com.example.gym4u_movile_app.models.FollowerUser
import com.example.gym4u_movile_app.models.Message
import com.example.gym4u_movile_app.models.resources.ConversationResource
import com.example.gym4u_movile_app.models.resources.MessageResource
import com.example.gym4u_movile_app.services.ConversationService
import com.example.gym4u_movile_app.services.MessageService
import com.example.gym4u_movile_app.util.RetrofitBuilder
import com.example.gym4u_movile_app.util.UtilFn
import com.example.gym4u_movile_app.util.UtilFn.Companion.toUTF8
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConversationFragment : Fragment() {
    private lateinit var binding: FragmentConversationBinding

    private val args: ConversationFragmentArgs by navArgs()
    private val retrofit = RetrofitBuilder.build()
    private val conversationService: ConversationService = retrofit.create(ConversationService::class.java)
    private val messageService: MessageService = retrofit.create(MessageService::class.java)
    private var conversation: Conversation? = null
    private val messages: ArrayList<Message> = ArrayList()

    private val conversationCallback = object : Callback<Conversation> {
        override fun onResponse(
            call: Call<Conversation>,
            response: Response<Conversation>
        ) {
            if(response.isSuccessful) {
                conversation = response.body()
                conversation?.messages?.let { messages.addAll(it) }
                binding.rvMessages.adapter?.notifyItemRangeInserted(0, messages.size)
            }
            else Log.d("conversationFragment", response.body().toString())

        }
        override fun onFailure(call: Call<Conversation>, t: Throwable) {
            Log.d("conversationFragment", t.toString())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConversationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickers()
    }
    override fun onResume() {
        super.onResume()
        initViews()
    }

    private fun initViews() {
        binding.rvMessages.layoutManager = LinearLayoutManager(context)
        binding.rvMessages.adapter = MessagesAdapter(messages, args.sender.id, args.receiver.id)
        loadMessages()
    }

    private fun initClickers() {
        binding.ibSendMessage.setOnClickListener {
            if (binding.etUserMessage.text.isNullOrEmpty())
                UtilFn.showShortToast(requireContext(), R.string.write_some_before_send)
            else sendMessage()
        }
    }

    private fun createConversationResource(coach: FollowerUser, client: FollowerUser) = ConversationResource(coach, client)
    private fun createConversation() {

        conversationService
            .createConversation(
                if(args.senderIsCoach)
                    createConversationResource(args.sender, args.receiver)
                else createConversationResource(args.receiver, args.sender)
            )
            .enqueue(conversationCallback)
    }
    private fun sendMessage() {
        if(conversation == null)
            createConversation()

        messageService
            .createMessage(
                MessageResource(
                    message = binding.etUserMessage.text.toString(),
                    conversation = conversation!!,
                    user = args.sender
                )
            ).enqueue(object : Callback<Message> {
                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    if(response.isSuccessful) {
                        messages.add(response.body()!!)
                        binding.rvMessages.adapter?.notifyItemChanged(messages.lastIndex)
                        binding.etUserMessage.setText("")

                    }
                    else Log.d("conversationFragment", "message didn't send")
                }

                override fun onFailure(call: Call<Message>, t: Throwable) {
                    Log.d("conversationFragment", t.toString())
                }

            })
    }

    private fun loadConversation(coach: FollowerUser, client: FollowerUser) {
        conversationService
            .getConversation(coach.id, client.id)
            .enqueue(conversationCallback)
    }

    private fun loadMessages() {
        val username = args.receiver.username.toUTF8()
        binding.tvConversationChatName.text = username
        binding.tvConversationChatAvatar.text = username[0].uppercase()

        if(args.senderIsCoach)
            loadConversation(args.sender, args.receiver)
        else loadConversation(args.receiver, args.sender)
    }

}