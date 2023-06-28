//fecha: 30/05/2023 15:44
package com.example.gym4u_movile_app

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gym4u_movile_app.databinding.ActivityItemExerciseeBinding
import com.example.gym4u_movile_app.databinding.ActivityNewWorkoutBinding
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Exercise
import com.example.gym4u_movile_app.entities.miSQL
import com.example.gym4u_movile_app.services.ExerciseService
import com.example.gym4u_movile_app.ui.clients.ExercisesAdapter
import com.example.gym4u_movile_app.util.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewWorkoutActivity : AppCompatActivity() {
    var workouts = ArrayList<Exercise>()//111111111111111111111
    var adapter22 = ExerciseAdapter(workouts);

    //private lateinit var bindingItem: ActivityItemExerciseeBinding

    private lateinit var binding: ActivityNewWorkoutBinding
    private lateinit var amigoos: miSQL
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_new_workout)
        binding = ActivityNewWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        amigoos = miSQL(this)



        db = amigoos.readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM exercises",null)

        val adapterAmigos = ExercisesAdapter()
        adapterAmigos.ExercisesAdapter(this,cursor)


        binding.rvExercisesQ.setHasFixedSize(true)
        binding.rvExercisesQ.layoutManager = LinearLayoutManager(this)
        binding.rvExercisesQ.adapter = adapterAmigos

        binding.btnAddNewExercises .setOnClickListener {
            val intent = Intent(this, AddExerciseActivity::class.java)
            startActivity(intent)
        }

        binding.btnAddWorkout.setOnClickListener {
            val intent = Intent(this, NewWorkoutActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
            val cursor: Cursor = db.rawQuery(
                "SELECT * FROM exercises",null)

            val adapterAmigos = ExercisesAdapter()
            adapterAmigos.ExercisesAdapter(this,cursor)

            binding.rvExercisesQ.setHasFixedSize(true)
            binding.rvExercisesQ.layoutManager = LinearLayoutManager(this)
            binding.rvExercisesQ.adapter = adapterAmigos
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }

    private fun InitView() {
        adapter22 = ExerciseAdapter(workouts)
        val rvPersonas = binding.rvExercises

        rvPersonas.adapter = adapter22
        rvPersonas.layoutManager = LinearLayoutManager(this)
    }

    private  fun loadExercises(){

        val retrofit = RetrofitBuilder.build()

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

