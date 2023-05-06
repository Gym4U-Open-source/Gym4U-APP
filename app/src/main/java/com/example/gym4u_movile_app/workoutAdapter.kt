package com.example.gym4u_movile_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.entities.Exercise
import com.example.gym4u_movile_app.entities.workout

class workoutAdapter(private val workout: ArrayList<workout>):
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


    fun bind(workout: workout){
        tvTituloWorkout.text= workout.titulo
        tvExercise1.text= workout.exercise1
        tvExercise2.text= workout.exercise2
        tvExercise3.text = workout.exercise3
    }

}


//class ExerciseAdapter (var exercises: ArrayList<Exercise>):
//    RecyclerView.Adapter<ExercisePrototype1>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisePrototype1 {
//        val view = LayoutInflater
//            .from(parent.context)
//            .inflate(R.layout.prototype_exercises, parent,false);
//        return  ExercisePrototype1(view)
//    }
//
//    override fun onBindViewHolder(holder: ExercisePrototype1, position: Int) {
//        holder.bind(exercises.get(position))
//    }
//
//    override fun getItemCount(): Int {
//        return exercises.size
//    }
//
//}
//
//
//class ExercisePrototype(itemView: View): RecyclerView.ViewHolder(itemView) {
//    val tvTimerExercise = itemView.findViewById<TextView>(R.id.tvTimerExercise)
//    val tvSetsExercise = itemView.findViewById<TextView>(R.id.tvSetsExercise)
//    val tvNameExercise = itemView.findViewById<TextView>(R.id.tvNameExercise)
//
//    fun bind(exercise: Exercise){
//        val aux: String= "time"
//        val aux1: Int = 10
//        //tvNameExercise.text = exercise.name
//        tvTimerExercise.text = aux
//        tvSetsExercise.text = aux1.toString()
//    }
//}