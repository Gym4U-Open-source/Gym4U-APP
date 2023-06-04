package com.example.gym4u_movile_app.ui.clients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.entities.Client

class ClientAdapter(var clients: List<Client>, val itemClickListener: OnItemClickListener): RecyclerView.Adapter<ClientPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientPrototype {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_client, parent, false)
        return ClientPrototype(view)
    }

    interface OnItemClickListener{
        fun OnItemClicked(client: Client)
    }

    override fun getItemCount(): Int {
        return clients.size
    }

    override fun onBindViewHolder(holder: ClientPrototype, position: Int) {
        holder.bind(clients.get(position))


        holder.btView.setOnClickListener{
            val client = clients[position]
            val clientFragment = ClientFragment()
            val bundle = Bundle().apply {
                putSerializable("client", client)
            }
            clientFragment.arguments = bundle

            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.container, clientFragment)
                .addToBackStack(null)
                .commit()



            //itemClickListener.OnItemClicked(client)
        }


        /*
        holder.btView.setOnClickListener{
            val clientFragment = ClientFragment()
            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.btView, clientFragment)
                .addToBackStack(null)
                .commit()
        }

         */

         
    }

}

class ClientPrototype(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvName = itemView.findViewById<TextView>(R.id.tvWorkoutName)
    val btView = itemView.findViewById<ImageButton>(R.id.ibView)
    fun bind(client: Client){
        tvName.text = client.name + " " + client.lastName
    }

}