package com.example.gym4u_movile_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.entities.Exercise

class ExercisesAdapter1(private val exercises: ArrayList<Exercise>):
    RecyclerView.Adapter<ExercisePrototype1>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisePrototype1 {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.prototype_exercises, parent, false
        )
        return ExercisePrototype1(view)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: ExercisePrototype1, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

}