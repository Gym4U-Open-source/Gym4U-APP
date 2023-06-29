package com.example.gym4u_movile_app.ui.clients

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.models.BaseResponse
import com.example.gym4u_movile_app.models.ClientWorkout
import com.example.gym4u_movile_app.models.Routines
import com.example.gym4u_movile_app.services.RoutineService
import com.example.gym4u_movile_app.util.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoutineFragment : Fragment() {

    lateinit var rvRoutines : RecyclerView
    lateinit var tvRoutineName : TextView
    private var workoutId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_routine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvRoutines = view.findViewById<RecyclerView>(R.id.rvRoutines)
        tvRoutineName = view.findViewById<TextView>(R.id.tvRoutineName)
        initWorkout()
        loadRoutines()


    }

    private fun loadRoutines() {
        val workoutIdInt : Int = workoutId?.toInt() ?:0

        val retrofit = RetrofitBuilder.build()

        val routineService : RoutineService
        routineService = retrofit.create(RoutineService::class.java)
        val request = routineService.getRoutines(workoutIdInt)

        request.enqueue(object: Callback<BaseResponse<Routines>>{
            override fun onResponse(call: Call<BaseResponse<Routines>>, response: Response<BaseResponse<Routines>>) {
                if(response.isSuccessful){
                    val routines: List<Routines> = response.body()!!.content
                    rvRoutines.adapter = RoutineAdapter(routines)
                    rvRoutines.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<BaseResponse<Routines>>, t: Throwable) {
                Log.d("Activity Fail", "Error: "+t.toString())
            }

        })

    }

    private fun initWorkout() {
        val workoutObject = arguments?.getSerializable("clientWorkout") as? ClientWorkout

        if (workoutObject != null) {
            val fullName = workoutObject.workout.name
            tvRoutineName.text = fullName
            workoutId = workoutObject.workout.id
        } else {
            tvRoutineName.text = "No se encontró ningún cliente"
        }
    }


    companion object {
        fun newInstance(param1: String, param2: String) =
            RoutineFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}