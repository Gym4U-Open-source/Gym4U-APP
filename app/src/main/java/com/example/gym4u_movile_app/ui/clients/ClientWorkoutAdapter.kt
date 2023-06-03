package com.example.gym4u_movile_app.ui.clients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.entities.Client
import com.example.gym4u_movile_app.entities.ClientWorkout

class ClientWorkoutAdapter(var clientsWorkouts: List<ClientWorkout>/*, val onDeleteClickListener: OnDeleteClickListener*/): RecyclerView.Adapter<ClientWorkoutPrototype>(){

    interface OnDeleteClickListener {
        fun onDeleteClick(clientWorkoutId: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientWorkoutPrototype {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_workout_client, parent, false)
        return ClientWorkoutPrototype(view)
    }

    override fun getItemCount(): Int {
        return clientsWorkouts.size
    }

    override fun onBindViewHolder(holder: ClientWorkoutPrototype, position: Int) {
        holder.bind(clientsWorkouts.get(position))
    }

}

class ClientWorkoutPrototype(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvWorkout = itemView.findViewById<TextView>(R.id.tvWorkoutName)
    val ibDelete = itemView.findViewById<ImageButton>(R.id.ibDelete)
    lateinit var onDeleteClickListener: ClientWorkoutAdapter.OnDeleteClickListener
    fun bind(clientWorkout: ClientWorkout){
        tvWorkout.text = clientWorkout.workout.name
        ibDelete.setOnClickListener {
            onDeleteClickListener.onDeleteClick(clientWorkout.id)
        }
    }
}
