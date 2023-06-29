package com.example.gym4u_movile_app.ui.library

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.models.Exercise

class ExerciseAdapter (private var exercises: ArrayList<Exercise>):
    RecyclerView.Adapter<ExercisePrototype1>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisePrototype1 {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_exercises, parent,false);
        return  ExercisePrototype1(view)
    }

    override fun onBindViewHolder(holder: ExercisePrototype1, position: Int) {
        holder.bind(exercises.get(position))
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

}


class ExercisePrototype(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvTimerExercise = itemView.findViewById<TextView>(R.id.tvTimerExercise)
    val tvSetsExercise = itemView.findViewById<TextView>(R.id.tvSetsExercise)
    val tvNameExercise = itemView.findViewById<TextView>(R.id.tvNameExercise)

    fun bind(exercise: Exercise){
        val aux: String= "time"
        val aux1: Int = 100
        //tvNameExercise.text = exercise.name
        tvTimerExercise.text = exercise.name
        tvSetsExercise.text = exercise.approach
    }
}