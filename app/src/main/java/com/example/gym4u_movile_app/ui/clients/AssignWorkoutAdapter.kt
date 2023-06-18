package com.example.gym4u_movile_app.ui.clients

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.gym4u_movile_app.entities.WORKOUTT
import java.util.Objects

class AssignWorkoutAdapter(context: Context, resource: Int, objects: List<WORKOUTT>) : ArrayAdapter<WORKOUTT>(context,resource,objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val item = getItem(position)

        // Configura la vista con el campo deseado de TuClase
        // Por ejemplo, si el campo deseado es "nombre":
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = item?.name

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val item = getItem(position)

        // Configura la vista desplegable con el campo deseado de TuClase
        // Por ejemplo, si el campo deseado es "nombre":
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = item?.name

        return view
    }

}