package com.example.gym4u_movile_app.ui.inbox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gym4u_movile_app.databinding.PrototypeInboxBinding
import com.example.gym4u_movile_app.entities.Follower
import com.example.gym4u_movile_app.util.UtilFn.Companion.toUTF8String


class InboxAdapter(private val chats: List<Follower>) : Adapter<InboxAdapter.ChatPrototype>() {
    class ChatPrototype(private val binding: PrototypeInboxBinding) : ViewHolder(binding.root) {
        fun bind(follower: Follower) {
            val username = toUTF8String(follower.clientUser.username)
            binding.tvChatAvatar.text = username[0].uppercase()
            binding.tvChatName.text = username
            binding.tvLastMessage.text = toUTF8String(follower.clientUser.email)
            binding.cvInbox.setOnClickListener {
                binding.root.findNavController().navigate(InboxFragmentDirections.actionNavigationInboxToInboxNavigation(follower))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatPrototype {
        return ChatPrototype(
            PrototypeInboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun onBindViewHolder(holder: ChatPrototype, position: Int) {
        holder.bind(chats[position])
    }
}