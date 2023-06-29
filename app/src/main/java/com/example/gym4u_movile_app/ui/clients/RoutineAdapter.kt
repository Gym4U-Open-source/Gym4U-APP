package com.example.gym4u_movile_app.ui.clients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.models.Routines

class RoutineAdapter(var routines: List<Routines>): RecyclerView.Adapter<RoutinePrototype>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutinePrototype {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_routine, parent, false)
        return RoutinePrototype(view)
    }

    override fun onBindViewHolder(holder: RoutinePrototype, position: Int) {
        holder.bind(routines.get(position))
    }

    override fun getItemCount(): Int {
        return routines.size
    }
}

class RoutinePrototype(itemView: View):RecyclerView.ViewHolder(itemView) {
    val tvPExercise = itemView.findViewById<TextView>(R.id.tvPExercise)
    val tvPRepetitions = itemView.findViewById<TextView>(R.id.tvPRepetitions)
    val tvPTime = itemView.findViewById<TextView>(R.id.tvPTime)

    fun bind(routine: Routines){
        tvPExercise.text = routine.exercise.name
        tvPRepetitions.text = routine.repetitions.toString()
        tvPTime.text = routine.timePerRepeat.toString() + "s"
    }
}
