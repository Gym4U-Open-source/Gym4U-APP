package com.example.gym4u_movile_app.ui.library

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gym4u_movile_app.R
import com.example.gym4u_movile_app.models.BaseResponse
import com.example.gym4u_movile_app.models.Library
import com.example.gym4u_movile_app.models.Tags
import com.example.gym4u_movile_app.services.LibraryService
import com.example.gym4u_movile_app.util.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LibraryActivity : AppCompatActivity() {

    var library= ArrayList<Library>()
    var libraryadapter= LibraryAdapter(library)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        val btnpopup= findViewById<Button>(R.id.btAddexcercise)
        btnpopup.setOnClickListener {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.popup_add_library, null)

            val alertDialogBuilder = AlertDialog.Builder(this)
                .setView(dialogView)

            val alertDialog = alertDialogBuilder.create()

            val EDTname= dialogView.findViewById<EditText>(R.id.addname)
            val EDTfocus= dialogView.findViewById<EditText>(R.id.addfocus)
            val EDTcategory= dialogView.findViewById<EditText>(R.id.addcategory)
            val EDvideo= dialogView.findViewById<EditText>(R.id.addvideo)
            val btnAddLibrary = dialogView.findViewById<Button>(R.id.btnAddLibrary)

            btnAddLibrary.setOnClickListener {
                val name= EDTname.text.toString()
                val focus= EDTfocus.text.toString()
                val category= EDTcategory.text.toString()
                val video= EDvideo.text.toString()
                library.add(Library(1,category,"asd",name,video,Tags(1,focus)))
                libraryadapter.notifyDataSetChanged()
                alertDialog.dismiss()
            }

            alertDialog.show()
        }

        loadlibrary()
        initview()
    }

    private fun initview() {
        val rvLibrary = findViewById<RecyclerView>(R.id.rvlibrary)

        // Configurar el LayoutManager
        val layoutManager = LinearLayoutManager(this)
        libraryadapter= LibraryAdapter(library)
        rvLibrary.layoutManager = layoutManager

        //Configurar el adaptador
        rvLibrary.adapter = libraryadapter

    }

    private fun loadlibrary() {


       val retrofit = RetrofitBuilder.build()

        val libraryService: LibraryService = retrofit.create(LibraryService::class.java)

        val request = libraryService.getAll()

        request.enqueue(object: Callback<BaseResponse<Library>> {
            override fun onResponse(
                call: Call<BaseResponse<Library>>,
                response: Response<BaseResponse<Library>>
            ) {
                response.body()!!.content.forEach {
                    library.add(it)
                }

                libraryadapter.notifyDataSetChanged()
                Log.d("Library: ", library[0].id.toString())
            }

            override fun onFailure(call: Call<BaseResponse<Library>>, t: Throwable) {
                Log.d("libraryAtivity", t.toString())
            }
        })

    }
}