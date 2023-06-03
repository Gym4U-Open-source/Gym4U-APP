package com.example.gym4u_movile_app.ui.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Library
import com.example.gym4u_movile_app.entities.Tag
import com.example.gym4u_movile_app.services.LibraryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class addexercise_popup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addexercise_popup)

        val name= findViewById<EditText>(R.id.addname)
        val focus= findViewById<EditText>(R.id.addprimary)
        val category= findViewById<EditText>(R.id.addcategory)
        val video= findViewById<EditText>(R.id.addvideo)

        val Na= name.text.toString()
        val Fo= focus.text.toString()
        val ca= category.text.toString()
        val url= video.text.toString()


        val btnadd= findViewById<Button>(R.id.addNewExercise)



        btnadd.setOnClickListener {

     /*       val newData = Library(1,ca, "s",Na,url, Tag(1,Fo)) // Reemplaza param1, param2, param3 con los valores reales

            val retrofit = Retrofit.Builder()
                .baseUrl("https://gym4u-api-388317.rj.r.appspot.com/") // Reemplaza con la URL base de tu servidor
                .addConverterFactory(GsonConverterFactory.create())
                .build()


            val libraryService: LibraryService = retrofit.create(LibraryService::class.java)


            val call = libraryService.addData(newData)
            call.enqueue(object : Callback<BaseResponse<Library>> {
                override fun onResponse(call: Call<BaseResponse<Library>>, response: Response<BaseResponse<Library>>) {
                    if (response.isSuccessful) {

                        val responseData = response.body()

                    } else {

                    }
                }

                override fun onFailure(call: Call<BaseResponse<Library>>, t: Throwable) {

                }
            })*/



            val fragmentContainer = findViewById<FrameLayout>(R.id.frameLayout2)

// Crea una instancia del Fragment que deseas mostrar
            val fragment = LibraryFragment()

// Obtén el FragmentManager
            val fragmentManager = supportFragmentManager

// Inicia una transacción
            val transaction = fragmentManager.beginTransaction()

// Reemplaza cualquier Fragment actual en el contenedor con el nuevo Fragment
            transaction.replace(fragmentContainer.id, fragment)

// Agrega la transacción a la pila de retroceso (opcional)
            transaction.addToBackStack(null)

// Realiza la transacción
            transaction.commit()



        }
    }
}