package com.example.gym4u_movile_app.ui.inbox

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gym4u_movile_app.databinding.PrototypeMessageBinding
import com.example.gym4u_movile_app.entities.Message
import com.example.gym4u_movile_app.util.UtilFn.Companion.toUTF8String


class MessagesAdapter(
    private val messages: List<Message>,
    private val from: Long,
    private val to: Long
) : Adapter<MessagesAdapter.Holder>() {

    inner class Holder(private val binding: PrototypeMessageBinding) : ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.tvUserMessage.text = toUTF8String(message.message)
            val parameters = binding.cvUserMessage.layoutParams as ConstraintLayout.LayoutParams
            if(to == message.userId)
                parameters.horizontalBias = 0.05f
            else if(from == message.userId)
                parameters.horizontalBias = 0.95f
            binding.cvUserMessage.layoutParams = parameters
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(PrototypeMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(messages[position])
    override fun getItemCount(): Int = messages.size

}