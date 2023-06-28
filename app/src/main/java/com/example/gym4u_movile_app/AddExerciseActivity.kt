package com.example.gym4u_movile_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gym4u_movile_app.databinding.ActivityAddExerciseBinding
import com.example.gym4u_movile_app.entities.miSQL
import com.example.gym4u_movile_app.util.UtilFn.Companion.showShortToast

class AddExerciseActivity : AppCompatActivity() {

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
                showShortToast("El ejercicio se ah guardado")
                finish()
            }
            else showShortToast("No se ah podido guardar el ejercicio")


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