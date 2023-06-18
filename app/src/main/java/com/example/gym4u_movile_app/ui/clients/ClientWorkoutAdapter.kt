package com.example.gym4u_movile_app.ui.clients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.ActivityCompat.recreate
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.entities.Client
import com.example.gym4u_movile_app.entities.ClientWorkout
import com.example.gym4u_movile_app.services.ClientWorkoutService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
        val clientWorkout = clientsWorkouts[position]
        holder.ibDelete.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.18.26:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val clientWorkoutService = retrofit.create(ClientWorkoutService::class.java)

            val request = clientWorkoutService.deleteClientWorkout(clientWorkout.id!!)

            request.enqueue(object: Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d("Activity Release", "Eliminated")
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("Activity Fail", "Error: "+t.toString())
                }
            })
        }

        holder.ibView.setOnClickListener {
            val routineFragment = RoutineFragment()
            val args = Bundle()
            args.putSerializable("clientWorkout", clientWorkout)
            routineFragment.arguments = args

            val navController = Navigation.findNavController(holder.ibView)
            navController.navigate(R.id.action_clientFragment_to_routineFragment, args)
        }

    }

}

class ClientWorkoutPrototype(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvWorkout = itemView.findViewById<TextView>(R.id.tvWorkoutName)
    val ibDelete = itemView.findViewById<ImageButton>(R.id.ibDelete)
    val ibView = itemView.findViewById<ImageButton>(R.id.ibView)

    fun bind(clientWorkout: ClientWorkout){
        tvWorkout.text = clientWorkout.workout.name
    }
}
