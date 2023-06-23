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
import com.example.gym4u_movile_app.entities.WORKOUTT
import com.example.gym4u_movile_app.services.WorkoutService
import com.example.gym4u_movile_app.util.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListWorkoutActivity : AppCompatActivity() {

    var workouts = ArrayList<WORKOUTT>();//1
    var adapterWorkout = WORKOUTSAdapter(workouts);//2
    private lateinit var handler: Handler
    private lateinit var updateTextRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_workout)

        loadData()
        initView()


        val btn_add_new_workouts = findViewById<Button>(R.id.btn_add_new_workouts2)
        btn_add_new_workouts.setOnClickListener{
            val intent = Intent(this, NewWorkoutActivity::class.java)
            startActivity(intent)
        }

        val tvCount = findViewById<TextView>(R.id.countWorkouts2)
        //tvCount.text = workouts.size.toString()
        handler = Handler()
        updateTextRunnable = object : Runnable {
            override fun run() {
                // Actualiza el contenido del TextView
                tvCount?.text = workouts.size.toString()

                // Programa la próxima actualización después de un intervalo de tiempo
                handler.postDelayed(this, 1000) // 1000 ms = 1 segundo
            }
        }

        super.onStart()

        // Inicia la primera actualización después de un intervalo de tiempo
        handler.postDelayed(updateTextRunnable, 1000) // 1000 ms = 1 segundo

    }

    private fun initView() {

        val recyclerViewAdapter = findViewById<RecyclerView>(R.id.rvWorkouts)
        val layoutManager = LinearLayoutManager(this)

        adapterWorkout = WORKOUTSAdapter(workouts);
        recyclerViewAdapter.layoutManager = layoutManager
        recyclerViewAdapter.adapter = adapterWorkout

    }

    private fun loadData() {
        val retrofit = RetrofitBuilder.build()

        val exerciseService: WorkoutService = retrofit.create(WorkoutService:: class.java)
        val request = exerciseService.getAll()

        request.enqueue(object: Callback<BaseResponse<WORKOUTT>> {
            override fun onResponse(
                call: Call<BaseResponse<WORKOUTT>>,
                response: Response<BaseResponse<WORKOUTT>>
            ) {
                response.body()!!.content.forEach {
                    workouts.add(it)
                }

                adapterWorkout.notifyDataSetChanged()
                Log.d("Exercise", workouts[0].id.toString())
            }

            override fun onFailure(call: Call<BaseResponse<WORKOUTT>>, t: Throwable) {
                Log.d("aaaa", t.toString())
            }
        })
    }
}