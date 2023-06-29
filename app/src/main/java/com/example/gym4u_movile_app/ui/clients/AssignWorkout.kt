package com.example.gym4u_movile_app.ui.clients

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.models.BaseResponse
import com.example.gym4u_movile_app.models.Client
import com.example.gym4u_movile_app.models.ClientWorkout
import com.example.gym4u_movile_app.models.WorkoutExercises
import com.example.gym4u_movile_app.services.ClientWorkoutService
import com.example.gym4u_movile_app.services.WorkoutService
import com.example.gym4u_movile_app.util.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AssignWorkout : Fragment() {

    lateinit var spWorkout: Spinner
    lateinit var btAssigned : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assign_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spWorkout = view.findViewById<Spinner>(R.id.spWorkout)
        btAssigned = view.findViewById<Button>(R.id.btAssigned)
        loadWorkouts()
    }

    private fun loadWorkouts() {

        val clientObject = arguments?.getSerializable("client") as? Client

        val retrofit = RetrofitBuilder.build()

        val workoutService: WorkoutService
        workoutService = retrofit.create(WorkoutService::class.java)
        val request = workoutService.getAll()

        request.enqueue(object : Callback<BaseResponse<WorkoutExercises>> {
            override fun onResponse(call: Call<BaseResponse<WorkoutExercises>>, response: Response<BaseResponse<WorkoutExercises>>) {
                if(response.isSuccessful){
                    val workouts: List<WorkoutExercises> = response.body()!!.content
                    val adapter = AssignWorkoutAdapter(requireContext(), android.R.layout.simple_spinner_item, workouts)
                    spWorkout.adapter = adapter

                    spWorkout.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                            val selectedWorkout: WorkoutExercises = workouts[p2]

                            btAssigned.setOnClickListener {

                                val retrofit2 = RetrofitBuilder.build()
                                val clientWorkoutService: ClientWorkoutService
                                clientWorkoutService = retrofit2.create(ClientWorkoutService::class.java)
                                val newClientWorkout = ClientWorkout(null,selectedWorkout, clientObject!!)
                                val request2 = clientWorkoutService.postUserWorkout(newClientWorkout)

                                request2.enqueue(object : Callback<Void>{
                                    override fun onResponse(call: Call<Void>, response: Response<Void>
                                    ) {
                                        Log.d("Activity Release", "Good")
                                    }

                                    override fun onFailure(call: Call<Void>, t: Throwable) {
                                        Log.d("Activity Fail", "Error: "+t.toString())
                                    }

                                })


                            }

                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {
                            Log.d("Activity Fail", "Error: ")
                        }

                    }

                }
            }

            override fun onFailure(call: Call<BaseResponse<WorkoutExercises>>, t: Throwable) {
                Log.d("Activity Fail", "Error: "+t.toString())
            }

        })

    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            AssignWorkout().apply {
                arguments = Bundle().apply {
                }
            }
    }
}