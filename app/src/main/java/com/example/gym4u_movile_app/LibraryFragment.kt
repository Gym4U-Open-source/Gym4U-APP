package com.example.gym4u_movile_app

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.databinding.FragmentPostsBinding
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Exercise
import com.example.gym4u_movile_app.entities.Post
import com.example.gym4u_movile_app.entities.workout
import com.example.gym4u_movile_app.services.ExerciseService
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
    var workouts = ArrayList<Exercise>()

    val adapter = ExercisesAdapter1(workouts)
//    private var _binding: FragmentPostsBinding? = null
//    private val binding get() = _binding!!

    ///
//    private var _binding: FragmentPostsBinding? =null
//    private val binding get() = _binding!!
    ///
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
        // Inflate the layout for this fragment
//        _binding = FragmentPostsBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//        loadExercises()
        val view = inflater.inflate(R.layout.fragment_library, container,false)

        val btn_add_new_workouts = view.findViewById<Button>(R.id.btn_add_new_workouts)

        val recyclerViewAdapter = view.findViewById<RecyclerView>(R.id.rvWorkouts)
        val layoutManager = LinearLayoutManager(context)
        var workouts_list = ArrayList<workout>()
        workouts_list.add(workout("Cardio","fondos al fallo", "Press banca", "Press militar"))
        workouts_list.add(workout("Hipertrofia","Curl de biceps", "Triceps en polea", "Peso muerto"))
        workouts_list.add(workout("Piernas","Sentadilla", "Zancada", "Extension de piernas"))
        workouts_list.add(workout("Cardio","fondos al fallo", "Press banca", "Press militar"))
        workouts_list.add(workout("Hipertrofia","Curl de biceps", "Triceps en polea", "Peso muerto"))
        workouts_list.add(workout("Piernas","Sentadilla", "Zancada", "Extension de piernas"))
        var adapter_workout = workoutAdapter(workouts_list)
        recyclerViewAdapter.layoutManager = layoutManager
        recyclerViewAdapter.adapter = adapter_workout


        btn_add_new_workouts.setOnClickListener {
            val popupView1 = layoutInflater.inflate(R.layout.activity_new_workout, null)
            val popupWindow1 = PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

            workouts.add(Exercise(1,"cardio","none",2,"Planchas",2,1))
            workouts.add(Exercise(1,"fuerza","none",2,"curl de biceps",2,1))
            workouts.add(Exercise(1,"resistencia","none",2,"Press de banca",2,1))
            workouts.add(Exercise(1,"elasticidad","none",2,"Fondos",2,1))
            workouts.add(Exercise(1,"cardio","none",2,"Planchas",2,1))
            workouts.add(Exercise(1,"fuerza","none",2,"curl de biceps",2,1))
            workouts.add(Exercise(1,"resistencia","none",2,"Press de banca",2,1))
            workouts.add(Exercise(1,"elasticidad","none",2,"Fondos",2,1))
            workouts.add(Exercise(1,"cardio","none",2,"Planchas",2,1))
            workouts.add(Exercise(1,"fuerza","none",2,"curl de biceps",2,1))
            workouts.add(Exercise(1,"resistencia","none",2,"Press de banca",2,1))
            workouts.add(Exercise(1,"elasticidad","none",2,"Fondos",2,1))

            popupWindow1.contentView = popupView1

            val view1 = view.findViewById<View>(R.id.frameLayout2)
            view1.setBackgroundColor(Color.parseColor("#99000000"))



            val btnAddWorkout= popupView1.findViewById<Button>(R.id.btn_add_workout)

            val recyclerView = popupView1.findViewById<RecyclerView>(R.id.rvExercises)
            val layoutManager = LinearLayoutManager(context)
            val adapter = ExerciseAdapter(workouts)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter


            btnAddWorkout.setOnClickListener {
                view1.setBackgroundColor(Color.parseColor("#D9D9D9"))
                popupWindow1.dismiss()
            }

            popupWindow1.width = ViewGroup.LayoutParams.WRAP_CONTENT
            popupWindow1.height  = ViewGroup.LayoutParams.WRAP_CONTENT
            popupWindow1.setBackgroundDrawable(ColorDrawable(Color.parseColor("#ffffff")))
            popupWindow1.showAtLocation(btn_add_new_workouts, Gravity.NO_GRAVITY or Gravity.CENTER_VERTICAL,10,0)

            //segunda ventana flotante

            val popupView2 = LayoutInflater.from(requireContext()).inflate(R.layout.activity_add_exercise, null)
            val popupWindow2 = PopupWindow(popupView2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

            popupWindow2.contentView = popupView2

            popupWindow2.width = ViewGroup.LayoutParams.WRAP_CONTENT
            popupWindow2.height = 1800

            val btnAddExercises = popupView1.findViewById<Button>(R.id.btn_add_new_exercises)
            btnAddExercises.setOnClickListener {
                val view2 = popupView1.findViewById<View>(R.id.vAddWorkout)
                view2.setBackgroundColor(Color.parseColor("#99000000"))
                popupWindow2.showAsDropDown(btnAddExercises)
                popupWindow2.showAtLocation(view, Gravity.CENTER,0,0)

                val btnAddExercise = popupView2.findViewById<Button>(R.id.btn_add_exercise)
                btnAddExercise.setOnClickListener {
                    view2.setBackgroundColor(Color.parseColor("#ffffff"))
                    popupWindow2.dismiss()
                 }

            }

        }

        return view

    }

    private fun loadExercises(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gym4u-api-384017.rj.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val exerciseService: ExerciseService = retrofit.create(ExerciseService::class.java)
        val request = exerciseService.getAll()

        request.enqueue(object : Callback<BaseResponse<Exercise>> {
            override fun onResponse(call: Call<BaseResponse<Exercise>>, response: Response<BaseResponse<Exercise>>) {
                if(response.isSuccessful){
                    workouts.addAll(response.body()?.content ?: emptyList())
                    adapter.notifyDataSetChanged()
                }

//                response.body()!!.content.forEach {
//                    workouts.add(it)
//                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<BaseResponse<Exercise>>, t: Throwable) {
                Log.d("Exercise", t.toString())
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



//if (response.isSuccessful) {
//    // Obtener los datos de la respuesta
//    val exercises = response.body()
//
//    // Mostrar los datos en el RecyclerView
//    val recyclerView = popupView1.findViewById<RecyclerView>(R.id.rvExercises)
//    val layoutManager = LinearLayoutManager(context)
//    val adapter = ExerciseAdapter(workouts)
//
//    recyclerView.layoutManager = layoutManager
//    recyclerView.adapter = adapter
//}




//        request.enqueue(object: Callback<BaseResponse<Exercise>> {
//            override fun onResponse(
//                call: Call<BaseResponse<Exercise>>?,
//                response: Response<BaseResponse<Exercise>>?) {
//
//                if (response!= null && response.isSuccessful){
//                    val exercises = response.body()?.data
//                    if (exercises!=null){
//                        adapter.(exercises)
//                        adapter.notifyDataSetChanged()
//                    }
//                }
//
////                response.body()!!.content.forEach{
////                    workouts.add(it)
////                }
////
////                adapter.notifyDataSetChanged()
////                Log.d("Exercise",workouts[0].id.toString())
//////                if (response.isSuccessful) {
//////                    exercisesLiveData.value = response.body()
//////                }
//            }
//
//            override fun onFailure(call: Call<BaseResponse<Exercise>>, t: Throwable) {
//                Log.d("Exercise", t.toString())
//            }
//            })


//val retrofit = Retrofit.Builder()
//    .baseUrl("https://jsonplaceholder.typicode.com/")
//    .addConverterFactory(GsonConverterFactory.create())
//    .build()
//
//val apiService = retrofit.create(ExerciseService::class.java)
//val request = apiService.getAll()
//
//request.enqueue(object : Callback<BaseResponse<Exercise>> {
//    override fun onResponse(call: Call<BaseResponse<Exercise>>, response: Response<BaseResponse<Exercise>>) {
//        if (response.isSuccessful) {
//            // Obtener los datos de la respuesta
//            val exercises = response.body()
//
//            // Mostrar los datos en el RecyclerView
//            val recyclerView = popupView1.findViewById<RecyclerView>(R.id.rvExercises)
//            val layoutManager = LinearLayoutManager(context)
//            val adapter = ExerciseAdapter(workouts)
//
//            recyclerView.layoutManager = layoutManager
//            recyclerView.adapter = adapter
//        }
//    }
//
//    override fun onFailure(call: Call<BaseResponse<Exercise>>, t: Throwable) {
//        Log.d("Exercise", t.toString())
//    }
//})


//override fun onResponse(call: Call<List<Exercise>>, response: Response<List<Exercise>>) {
//    if (response.isSuccessful) {
//        // Obtener los datos de la respuesta
//        val exercises = response.body()
//
//        // Mostrar los datos en el RecyclerView
//        val recyclerView = popupView1.findViewById<RecyclerView>(R.id.rvExercises)
//        val layoutManager = LinearLayoutManager(context)
//        val adapter = ExerciseAdapter(workouts)
//
//        recyclerView.layoutManager = layoutManager
//        recyclerView.adapter = adapter
//    }
//}
//
//override fun onFailure(call: Call<List<Exercise>>, t: Throwable) {
// Manejar el error

//
//package com.example.gym4u_movile_app
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.gym4u_movile_app.entities.Exercise
//
//class new_workout : AppCompatActivity() {
//
//    var workouts = ArrayList<Exercise>()
//    //    var exerciseAdapter = ExerciseAdapter(workouts)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_new_workout)
//
//        loadExercises()
//        val recycleView = findViewById<RecyclerView>(R.id.rvExercises)
//        val layoutManager = LinearLayoutManager(this)
//        val adapter = ExercisesAdapter1(workouts)
//        recycleView.layoutManager= layoutManager
//        recycleView.adapter = adapter
//    }

//    private fun loadExercises(){
//        workouts.add(Exercise("Cardio","5","50"))
//        workouts.add(Exercise("Fuerza","2","40"))
//        workouts.add(Exercise("Resistencia","1","30"))
//        workouts.add(Exercise(1,"Cardio","urlimagen",1,"Abdominales",1, 1))
//        workouts.add(Exercise(1,"Fuerza","urlimagen",1,"Curl de biceps",1, 1))
//        workouts.add(Exercise(1,"Resistencia","urlimagen",1,"press",1, 1))
//        workouts.add(Exercise(1,"Hipertrofia","urlimagen",1,"Fondos",1, 1))
//    }

//    private fun initView(){
//        val rvExercise = findViewById<RecyclerView>(R.id.rvExercises)
//        rvExercise.adapter = exerciseAdapter
//        rvExercise.layoutManager = LinearLayoutManager(this)
//    }
//}