package com.example.gym4u_movile_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.entities.Exercise

class new_workout : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_workout)
//        var workouts = ArrayList<Exercise>()
//        workouts.add(Exercise("Cardio","5","50"))
//        workouts.add(Exercise("Fuerza","2","40"))
//        workouts.add(Exercise("Resistencia","1","30"))

//    val recycleView = findViewById<RecyclerView>(R.id.rvExercises)
//    val layoutManager = LinearLayoutManager(this)
//    val adapter = ExercisesAdapter1(workouts)
//    recycleView.layoutManager= layoutManager
//    recycleView.adapter = adapter
    }

    private fun loadExercises(){

//        workouts.add(Exercise(1,"Cardio","urlimagen",1,"Abdominales",1, 1))
//        workouts.add(Exercise(1,"Fuerza","urlimagen",1,"Curl de biceps",1, 1))
//        workouts.add(Exercise(1,"Resistencia","urlimagen",1,"press",1, 1))
//        workouts.add(Exercise(1,"Hipertrofia","urlimagen",1,"Fondos",1, 1))
    }

//    private fun initView(){
//        val rvExercise = findViewById<RecyclerView>(R.id.rvExercises)
//        rvExercise.adapter = exerciseAdapter
//        rvExercise.layoutManager = LinearLayoutManager(this)
//    }
}