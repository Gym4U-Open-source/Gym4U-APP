package com.example.gym4u_movile_app.ui.register.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.gym4u_movile_app.databinding.PrototypeTermsAndConditionsBinding

class TermsAndConditionsAdapter(
    private val terms: List<Pair<String, String>>
): Adapter<TermsAndConditionsAdapter.Holder>() {
    inner class Holder(private val binding: PrototypeTermsAndConditionsBinding) : ViewHolder(binding.root) {
        fun bind(term: Pair<String, String>, position: Int) {
            if(term.first.isEmpty())
                binding.tvTermsTitle.visibility = View.GONE
            binding.tvTermsTitle.text = "${position}. ${term.first}"
            binding.tvTermsDescription.text = term.second
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(PrototypeTermsAndConditionsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = terms.size

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(terms[position], position)
}