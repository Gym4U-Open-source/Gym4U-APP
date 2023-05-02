package com.example.gym4u_movile_app

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow

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

        val view = inflater.inflate(R.layout.fragment_library, container,false)

        val btn_add_new_workouts = view.findViewById<Button>(R.id.btn_add_new_workouts)
        btn_add_new_workouts.setOnClickListener {
            val popupWindow1 = PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
            val popupView1 = layoutInflater.inflate(R.layout.activity_new_workout, null)
            popupWindow1.contentView = popupView1

            val view1 = view.findViewById<View>(R.id.frameLayout2)
            view1.setBackgroundColor(Color.parseColor("#99000000"))

            val btnAddWorkout= popupView1.findViewById<Button>(R.id.btn_add_workout)
            btnAddWorkout.setOnClickListener {
                view1.setBackgroundColor(Color.parseColor("#ffffff"))
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