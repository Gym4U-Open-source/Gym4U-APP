package com.example.gym4u_movile_app.ui.clients

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Client
import com.example.gym4u_movile_app.entities.ClientWorkout
import com.example.gym4u_movile_app.entities.WORKOUTT
import com.example.gym4u_movile_app.services.ClientWorkoutService
import com.example.gym4u_movile_app.services.WorkoutService
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.18.26:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val workoutService: WorkoutService
        workoutService = retrofit.create(WorkoutService::class.java)
        val request = workoutService.getAll()

        request.enqueue(object : Callback<BaseResponse<WORKOUTT>> {
            override fun onResponse(call: Call<BaseResponse<WORKOUTT>>, response: Response<BaseResponse<WORKOUTT>>) {
                if(response.isSuccessful){
                    val workouts: List<WORKOUTT> = response.body()!!.content
                    val adapter = AssignWorkoutAdapter(requireContext(), android.R.layout.simple_spinner_item, workouts)
                    spWorkout.adapter = adapter

                    spWorkout.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                            val selectedWorkout: WORKOUTT = workouts[p2]

                            btAssigned.setOnClickListener {

                                val retrofit2 = Retrofit.Builder()
                                    .baseUrl("http://192.168.18.26:8080/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build()
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

            override fun onFailure(call: Call<BaseResponse<WORKOUTT>>, t: Throwable) {
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