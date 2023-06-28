
package com.example.gym4u_movile_app.ui.library

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gym4u_movile_app.ListWorkoutActivity
import com.example.gym4u_movile_app.R


class LibraryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_library, container,false)

        val tvPrueba = view.findViewById<TextView>(R.id.tvExercise)
        tvPrueba.setOnClickListener{
            val intenttt = Intent(requireContext(), LibraryActivity::class.java)
            startActivity(intenttt)
        }

        val tvPrueba1 = view.findViewById<TextView>(R.id.tvWorkouts)
        tvPrueba1.setOnClickListener{
            val intentt = Intent(requireContext(), ListWorkoutActivity::class.java)
            startActivity(intentt)
        }

        return view
    }


}


