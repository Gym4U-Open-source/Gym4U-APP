package com.example.gym4u_movile_app

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CursorAdapter
import android.widget.Toast
import com.example.gym4u_movile_app.databinding.ActivityAddExerciseBinding
import com.example.gym4u_movile_app.entities.miSQL

class add_exercise : AppCompatActivity() {

    lateinit var binding: ActivityAddExerciseBinding
    lateinit var exercisesDb: miSQL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add_exercise)

        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        exercisesDb = miSQL(this)



        //val agregarEjericio = findViewById<Button>(R.id.btn_add_exercise)
        binding.btnAddExercise.setOnClickListener {


            if (binding.inputName.text!!.isNotBlank() &&
                binding.inputUrlImage.text!!.isNotBlank() &&
                binding.inputCountSet.text!!.isNotBlank() &&
                binding.inputTimeSet.text!!.isNotBlank()
                    ){
                exercisesDb.addExercises(binding.inputName.text.toString(),
                    binding.inputCountSet.text.toString(),
                    binding.inputTimeSet.text.toString(),
                    binding.inputUrlImage.text.toString()
                    )
                binding.inputName.text!!.clear()
                binding.inputUrlImage.text!!.clear()
                binding.inputCountSet.text!!.clear()
                binding.inputTimeSet.text!!.clear()
                Toast.makeText(this,"El ejercicio se ah guardado", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"No se ah podido guardar el ejercicio", Toast.LENGTH_SHORT).show()
            }


//            binding.datos.text = ""
//            val db: SQLiteDatabase = exercisesDb.readableDatabase
//            val curso = db.rawQuery("SELECT * FROM exercises", null)
//
//            if (curso.moveToFirst()){
//                do {
//                    binding.datos.append(
//                        curso.getInt(0).toString() + ": ")
//                    binding.datos.append(
//                        curso.getString(1).toString() + ": ")
//                    binding.datos.append(
//                        curso.getString(2).toString() + ": ")
//                    binding.datos.append(
//                        curso.getString(3).toString() + ": ")
//                    binding.datos.append(
//                        curso.getString(4).toString() + "\n")
//                }while (curso.moveToNext())
//            }




        }
    }
}