package com.example.gym4u_movile_app.ui.library

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.models.Exercise

class ExercisePrototype1(itemView: View): RecyclerView.ViewHolder(itemView){

    val tvNameExercise = itemView.findViewById<TextView>(R.id.tvNameExercise)
    val tvSetsExercise = itemView.findViewById<TextView>(R.id.tvSetsExercise)
    val tvTimerExercise = itemView.findViewById<TextView>(R.id.tvTimerExercise)


    fun bind(exercise: Exercise){
        val Sets = 100
        val Timer = 2
        tvNameExercise.text= exercise.name
        tvSetsExercise.text= Sets.toString()
        tvTimerExercise.text = Timer.toString()
    }

}