//este es el del github

package com.example.gym4u_movile_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.WORKOUTT
import com.example.gym4u_movile_app.services.WorkoutService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LibraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LibraryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var workouts = ArrayList<WORKOUTT>();//1
    var adapterWorkout = WORKOUTSAdapter(workouts);//2
    private lateinit var handler: Handler
    private lateinit var updateTextRunnable: Runnable
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_library, container,false)
        loadData()
        initView(view)
        val tvCount = view.findViewById<TextView>(R.id.countWorkouts)
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


        return view
    }

    private fun initView(view: View) {
        val btn_add_new_workouts = view.findViewById<Button>(R.id.btn_add_new_workouts)
        val recyclerViewAdapter = view.findViewById<RecyclerView>(R.id.rvWorkouts)
        val layoutManager = LinearLayoutManager(context)


        adapterWorkout = WORKOUTSAdapter(workouts);
        recyclerViewAdapter.layoutManager = layoutManager
        recyclerViewAdapter.adapter = adapterWorkout
        btn_add_new_workouts.setOnClickListener{
            val intent = Intent(activity, new_workout::class.java)
            startActivity(intent)
        }
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gym4u-api-388317.rj.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val exerciseService: WorkoutService = retrofit.create(WorkoutService:: class.java)
        val request = exerciseService.getAll()

        request.enqueue(object: Callback<BaseResponse<WORKOUTT>>{
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


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LibraryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LibraryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}


