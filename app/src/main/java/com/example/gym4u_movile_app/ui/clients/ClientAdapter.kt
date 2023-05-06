package com.example.gym4u_movile_app.ui.clients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R

class ClientAdapter(var clients: ArrayList<Client>): RecyclerView.Adapter<ClientPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientPrototype {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototyoe_client, parent, false)
        return ClientPrototype(view)
    }

    override fun getItemCount(): Int {
        return clients.size
    }

    override fun onBindViewHolder(holder: ClientPrototype, position: Int) {
        holder.bind(clients.get(position))
    }

}

class ClientPrototype(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.findViewById<TextView>(R.id.tvName)

    fun bind(client: Client){
        tvName.text = client.name
    }

}
