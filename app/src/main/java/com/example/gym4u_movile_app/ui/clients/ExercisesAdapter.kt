package com.example.gym4u_movile_app.ui.clients

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.databinding.ActivityItemExerciseeBinding

class ExercisesAdapter
    : RecyclerView.Adapter<ExercisesAdapter.ViewHolder>(){

    lateinit var context: Context//
    lateinit var cursor: Cursor//

    fun ExercisesAdapter(context: Context, cursor: Cursor){//
        this.context=context//
        this.cursor = cursor//
    }

    inner class ViewHolder: RecyclerView.ViewHolder{//
        val tvName: TextView
        val tvUrl: TextView
        val tvSets: TextView
        val tvTime: TextView
        constructor(view: View): super(view){
            val bindingItemVr = ActivityItemExerciseeBinding.bind(view)
            tvName = bindingItemVr.tvName
            tvUrl = bindingItemVr.tvUrl
            tvSets = bindingItemVr.tvSets
            tvTime = bindingItemVr.tvTimeItem

            view.setOnClickListener {
                Toast.makeText(context,
                    "${tvName.text}, ${tvUrl}, ${tvSets}, ${tvTime}",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.activity_item_exercisee,parent, false))
    }

    override fun getItemCount(): Int {
        if (cursor==null)
            return 0
        else
            return cursor.count
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)

        holder.tvName.text = cursor.getString(1)
        holder.tvUrl.text = cursor.getString(2)
        holder.tvSets.text = cursor.getString(3)
        holder.tvTime.text = cursor.getString(4)
    }
}