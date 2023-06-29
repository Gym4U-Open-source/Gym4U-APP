package com.example.gym4u_movile_app.ui.clients

import android.content.Context
import android.database.Cursor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.databinding.ActivityItemExerciseeBinding
import com.example.gym4u_movile_app.database.miSQLWorkouts
import com.example.gym4u_movile_app.util.UtilFn

class ExercisesAdapter
    : RecyclerView.Adapter<ExercisesAdapter.ViewHolder>(){

    lateinit var context: Context//
    lateinit var cursor: Cursor//
    lateinit var exercisesDb: miSQLWorkouts

    fun ExercisesAdapter(context: Context, cursor: Cursor){//
        this.context=context//
        this.cursor = cursor//

    }

    inner class ViewHolder: RecyclerView.ViewHolder{//
        val tvName: TextView
        val tvUrl: TextView
        val tvSets: TextView
        val tvTime: TextView
//        val btnAddWorkout_ : Button
        var contador: Int



//        var nameWorkout: String
        var name: String
        var account: String
        var time: String
        var assetUrl: String
        var name1: String
        var account1: String
        var time1: String
        var assetUrl1: String
        var name2: String
        var account2: String
        var time2: String
        var assetUrl2: String


        val btnAdd: Button
//        val etNameWorkout: EditText
        constructor(view: View): super(view){
            val bindingItemVr = ActivityItemExerciseeBinding.bind(view)
//            val bindinNewWorkout = ActivityNewWorkoutBinding.bind(view)
    exercisesDb = miSQLWorkouts(context)
            tvName = bindingItemVr.tvName
            tvUrl = bindingItemVr.tvUrl
            tvSets = bindingItemVr.tvSets
            tvTime = bindingItemVr.tvTimeItem

            btnAdd = bindingItemVr.button

            view.setOnClickListener {
                Toast.makeText(context,
                    "${tvName.text}, ${tvUrl}, ${tvSets}, ${tvTime}",
                    Toast.LENGTH_SHORT).show()
            }

//            btnAddWorkout_ = bindinNewWorkout.btnAddWorkout
//            etNameWorkout = bindinNewWorkout.inputNameWorkout

            contador = 0


            name= ""
            account= ""
            time= ""
            assetUrl= ""

            name1= ""
            account1= ""
            time1= ""
            assetUrl1= ""

            name2= ""
            account2= ""
            time2= ""
            assetUrl2= ""

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



        holder.btnAdd.setOnClickListener {
            //Log.d("Funcionaaaaa: ", holder.tvName.text.toString())
            UtilFn.Companion.showShortToast(context, "Se seleccionó correctamente")
            holder.contador ++
            if (holder.contador == 1){
                holder.name= holder.tvName.text.toString()
                holder.account= holder.tvSets.text.toString()
                holder.time= holder.tvTime.text.toString()
                holder.assetUrl= holder.tvUrl.text.toString()
            }
            else if (holder.contador == 2){
                holder.name1= holder.tvName.text.toString()
                holder.account1= holder.tvSets.text.toString()
                holder.time1= holder.tvTime.text.toString()
                holder.assetUrl1= holder.tvUrl.text.toString()
            }
            else{
                holder.name2= holder.tvName.text.toString()
                holder.account2= holder.tvSets.text.toString()
                holder.time2= holder.tvTime.text.toString()
                holder.assetUrl2= holder.tvUrl.text.toString()
            }

            if (holder.contador == 3){
                addWorkout(
                    holder.name,holder.account,holder.time,holder.assetUrl,
                    holder.name1,holder.account1,holder.time1,holder.assetUrl1,
                    holder.name2,holder.account2,holder.time2,holder.assetUrl2
                )
                Log.d("se agrego: ", holder.contador.toString())
            }
            else{
                Log.d("NO se agrego: ", holder.contador.toString())
            }
        }

//        holder.btnAddWorkout_.setOnClickListener {
//            if (holder.etNameWorkout.text.toString()!==""){
//                addWorkout(
//                    holder.etNameWorkout.text.toString(),
//                    holder.name,holder.account,holder.time,holder.assetUrl,
//                    holder.name1,holder.account1,holder.time1,holder.assetUrl1,
//                    holder.name2,holder.account2,holder.time2,holder.assetUrl2
//                )
//                UtilFn.Companion.showShortToast(context, "Se agregó el workout correctamente")
//            }
//            else{
//                UtilFn.Companion.showShortToast(context, "No se pudo agregar el workout")
//            }
//        }
    }

    fun addWorkout(name: String, account: String, time: String, assetUrl: String,
                   name1: String, account1: String, time1: String, assetUrl1: String,
                   name2: String, account2: String, time2: String, assetUrl2: String){
        exercisesDb.addExercises("workout",
            name, account, time, assetUrl,
            name1, account1, time1, assetUrl1,
            name2, account2, time2, assetUrl2)
    }
}