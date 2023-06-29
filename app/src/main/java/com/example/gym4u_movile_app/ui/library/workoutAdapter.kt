package com.example.gym4u_movile_app.ui.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.models.WorkoutExercises
import com.example.gym4u_movile_app.models.Workout

class workoutAdapter(private val workout: ArrayList<Workout>):
    RecyclerView.Adapter<WorkoutPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_workout, parent,false);
        return  WorkoutPrototype(view)
    }

    override fun getItemCount(): Int {
        return workout.size
    }

    override fun onBindViewHolder(holder: WorkoutPrototype, position: Int) {
        holder.bind(workout.get(position))
    }


}

class WorkoutPrototype(itemView: View): RecyclerView.ViewHolder(itemView){

    val tvTituloWorkout = itemView.findViewById<TextView>(R.id.tvTituloWorkout)
    val tvExercise1 = itemView.findViewById<TextView>(R.id.tvExercise1)
    val tvExercise2 = itemView.findViewById<TextView>(R.id.tvExercise2)
    val tvExercise3 = itemView.findViewById<TextView>(R.id.tvExercise3)


    fun bind(workout: Workout){
        tvTituloWorkout.text= workout.titulo
        tvExercise1.text= workout.exercise1
        tvExercise2.text= workout.exercise2
        tvExercise3.text = workout.exercise3
    }

}




class WORKOUTSAdapter(private val WORKOUTS: ArrayList<WorkoutExercises>):
    RecyclerView.Adapter<WORKOUTSPrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WORKOUTSPrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_workout, parent,false);
        return  WORKOUTSPrototype(view)
    }

    override fun getItemCount(): Int {
        return WORKOUTS.size
    }

    override fun onBindViewHolder(holder: WORKOUTSPrototype, position: Int) {
        holder.bind(WORKOUTS.get(position))
    }

}


class WORKOUTSPrototype(itemView: View): RecyclerView.ViewHolder(itemView){

    val tvTituloWorkout = itemView.findViewById<TextView>(R.id.tvTituloWorkout)
    val tvExercise1 = itemView.findViewById<TextView>(R.id.tvExercise1)
    val tvExercise2 = itemView.findViewById<TextView>(R.id.tvExercise2)
    val tvExercise3 = itemView.findViewById<TextView>(R.id.tvExercise3)

    fun bind(WORKOUTS: WorkoutExercises){
        if (WORKOUTS.routines.size>=3){
            tvTituloWorkout.text= WORKOUTS.name
            tvExercise1.text= WORKOUTS.routines[0].exercise.name
            tvExercise2.text= WORKOUTS.routines[1].exercise.name
            tvExercise3.text = WORKOUTS.routines[2].exercise.name
        }
        else if(WORKOUTS.routines.size==2){
            tvTituloWorkout.text= WORKOUTS.name
            tvExercise1.text= WORKOUTS.routines[0].exercise.name
            tvExercise2.text= WORKOUTS.routines[1].exercise.name
            tvExercise3.text = ""
        }
        else if(WORKOUTS.routines.size==1){
            tvTituloWorkout.text= WORKOUTS.name
            tvExercise1.text= WORKOUTS.routines[0].exercise.name
            tvExercise2.text= ""
            tvExercise3.text = ""
        }
        else{
            tvTituloWorkout.text= WORKOUTS.name
            tvExercise1.text= ""
            tvExercise2.text= ""
            tvExercise3.text = ""
        }
    }

}