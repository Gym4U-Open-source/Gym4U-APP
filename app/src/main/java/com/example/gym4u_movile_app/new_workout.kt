//fecha: 30/05/2023 15:44
package com.example.gym4u_movile_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Exercise
import com.example.gym4u_movile_app.services.ExerciseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class new_workout : AppCompatActivity() {
    var workouts = ArrayList<Exercise>()//111111111111111111111
    var adapter22 = ExerciseAdapter(workouts);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_workout)
        loadExercises()
        InitView()

        val agregarEjericio = findViewById<Button>(R.id.btn_add_new_exercises)
        agregarEjericio.setOnClickListener {
            val intent = Intent(this, add_exercise::class.java)
            startActivity(intent)
        }

        val agregarWorkout = findViewById<Button>(R.id.btn_add_workout)
        agregarWorkout.setOnClickListener {
            val fragmentManager = supportFragmentManager  // O getFragmentManager() para actividades heredadas de Activity
            val fragment = LibraryFragment()  // Crea una instancia del fragmento que deseas mostrar
            val transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.container, fragment)  // Agrega el fragmento al contenedor
            transaction.commit()
        }

    }

    private fun InitView() {
        adapter22 = ExerciseAdapter(workouts)//3
        val rvPersonas = findViewById<RecyclerView>(R.id.rvExercises)

        rvPersonas.adapter = adapter22
        rvPersonas.layoutManager = LinearLayoutManager(this)
    }

    private  fun loadExercises(){
//        workouts.add(Exercise(1,"cardio","none",2,"Planchas",2,1))
//        workouts.add(Exercise(1,"fuerza","none",2,"curl de biceps",2,1))
//        workouts.add(Exercise(1,"resistencia","none",2,"Press de banca",2,1))
//        workouts.add(Exercise(1,"elasticidad","none",2,"Fondos",2,1))
//        workouts.add(Exercise(1,"cardio","none",2,"Planchas",2,1))
//        workouts.add(Exercise(1,"fuerza","none",2,"curl de biceps",2,1))
//        workouts.add(Exercise(1,"resistencia","none",2,"Press de banca",2,1))
//        workouts.add(Exercise(1,"elasticidad","none",2,"Fondos",2,1))
//        workouts.add(Exercise(1,"cardio","none",2,"Planchas",2,1))
//        workouts.add(Exercise(1,"fuerza","none",2,"curl de biceps",2,1))
//        workouts.add(Exercise(1,"resistencia","none",2,"Press de banca",2,1))
//        workouts.add(Exercise(1,"elasticidad","none",2,"Fondos",2,1))

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gym4u-api-388317.rj.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val exerciseService: ExerciseService = retrofit.create(ExerciseService:: class.java)

        val request = exerciseService.getAll()

        request.enqueue(object: Callback<BaseResponse<Exercise>>{
            override fun onResponse(
                call: Call<BaseResponse<Exercise>>,
                response: Response<BaseResponse<Exercise>>
            ) {
                response.body()!!.content.forEach {
                    workouts.add(it)
                }

                adapter22.notifyDataSetChanged()
                Log.d("Exercise", workouts[0].id.toString())
            }

            override fun onFailure(call: Call<BaseResponse<Exercise>>, t: Throwable) {
                Log.d("aaaa", t.toString())
            }
        })

    }


}


//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo

//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//abajooooooo
//
//package com.example.gym4u_movile_app
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.gym4u_movile_app.entities.BaseResponse
//import com.example.gym4u_movile_app.entities.Exercise
//import com.example.gym4u_movile_app.services.ExerciseService
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class new_workout : AppCompatActivity() {
//    var workouts = ArrayList<Exercise>()//111111111111111111111
//    var adapter22 = ExerciseAdapter(workouts);
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_new_workout)
//        val fragmentManager = supportFragmentManager
//        val fragmentoExistente = fragmentManager.findFragmentById(R.id.frameLayout2)
//        loadExercises()
//        InitView()
////        val agregarEjericio = findViewById<Button>(R.id.btn_add_new_exercises)
////        agregarEjericio.setOnClickListener {
////            val intent = Intent(this, add_exercise::class.java)
////            startActivity(intent)
////        }
//        val agregarWorkout =findViewById<Button>(R.id.btn_add_workout)
//        agregarWorkout.setOnClickListener{
////            val intent = Intent(fragment, LibraryFragment::class.java)
////            startActivity(intent)
//
//
//
//            if (fragmentoExistente != null) {
//                fragmentManager.beginTransaction()
//                    .show(fragmentoExistente)
//                    .commit()
//            }
//        }
////        var workouts = ArrayList<Exercise>()
////        workouts.add(Exercise("Cardio","5","50"))
////        workouts.add(Exercise("Fuerza","2","40"))
////        workouts.add(Exercise("Resistencia","1","30"))
//
////    val recycleView = findViewById<RecyclerView>(R.id.rvExercises)
////    val layoutManager = LinearLayoutManager(this)
////    val adapter = ExercisesAdapter1(workouts)
////    recycleView.layoutManager= layoutManager
////    recycleView.adapter = adapter
//    }
//
//    private fun InitView() {
//        adapter22 = ExerciseAdapter(workouts)//3
//        val rvPersonas = findViewById<RecyclerView>(R.id.rvExercises)
//
//        rvPersonas.adapter = adapter22
//        rvPersonas.layoutManager = LinearLayoutManager(this)
//
////        val rv = findViewById<RecyclerView>(R.id.rv)
////        rv.adapter = adapter1
////        rv.layoutManager = LinearLayoutManager(this)
//    }
//
//    private  fun loadExercises(){
//        workouts.add(Exercise(1,"cardio","none",2,"Planchas",2,1))
//        workouts.add(Exercise(1,"fuerza","none",2,"curl de biceps",2,1))
//        workouts.add(Exercise(1,"resistencia","none",2,"Press de banca",2,1))
//        workouts.add(Exercise(1,"elasticidad","none",2,"Fondos",2,1))
//        workouts.add(Exercise(1,"cardio","none",2,"Planchas",2,1))
//        workouts.add(Exercise(1,"fuerza","none",2,"curl de biceps",2,1))
//        workouts.add(Exercise(1,"resistencia","none",2,"Press de banca",2,1))
//        workouts.add(Exercise(1,"elasticidad","none",2,"Fondos",2,1))
//        workouts.add(Exercise(1,"cardio","none",2,"Planchas",2,1))
//        workouts.add(Exercise(1,"fuerza","none",2,"curl de biceps",2,1))
//        workouts.add(Exercise(1,"resistencia","none",2,"Press de banca",2,1))
//        workouts.add(Exercise(1,"elasticidad","none",2,"Fondos",2,1))
////
////        val retrofit = Retrofit.Builder()
////            .baseUrl("https://localhost:3000/")
////            .addConverterFactory(GsonConverterFactory.create())
////            .build()
////
////        val exerciseService: ExerciseService = retrofit.create(ExerciseService::class.java)
////        val request = exerciseService.getAll()
////
////        request.enqueue(object : Callback<BaseResponse<Exercise>> {
////            override fun onResponse(
////                call: Call<BaseResponse<Exercise>>,
////                response: Response<BaseResponse<Exercise>>
////            ) {
//////                if(response.isSuccessful){
//////                    workouts.addAll(response.body()?.content ?: emptyList())
//////                    adapter.notifyDataSetChanged()
//////                }
////
////                response.body()!!.content.forEach {
////                    workouts.add(it)
////                }
////                adapter22.notifyDataSetChanged()
////                Log.d("Exercise", workouts[0].id.toString())
////            }
////
////            override fun onFailure(call: Call<BaseResponse<Exercise>>, t: Throwable) {
////                Log.d("Exercise", t.toString())
////            }
////        })
//    }
//
//
//    //private fun loadExercises(){
//
////        workouts.add(Exercise(1,"Cardio","urlimagen",1,"Abdominales",1, 1))
////        workouts.add(Exercise(1,"Fuerza","urlimagen",1,"Curl de biceps",1, 1))
////        workouts.add(Exercise(1,"Resistencia","urlimagen",1,"press",1, 1))
////        workouts.add(Exercise(1,"Hipertrofia","urlimagen",1,"Fondos",1, 1))
//    // }
//
////    private fun initView(){
////        val rvExercise = findViewById<RecyclerView>(R.id.rvExercises)
////        rvExercise.adapter = exerciseAdapter
////        rvExercise.layoutManager = LinearLayoutManager(this)
////    }
//}