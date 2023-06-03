package com.example.gym4u_movile_app.ui.clients

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.entities.ClientWorkout

class ClientWorkoutAdapter(var clientsWorkouts: List<ClientWorkout>): RecyclerView.Adapter<ClientWorkoutPrototype>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientWorkoutPrototype {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ClientWorkoutPrototype, position: Int) {
        TODO("Not yet implemented")
    }

}

class ClientWorkoutPrototype(itemView: View): RecyclerView.ViewHolder(itemView) {

}
