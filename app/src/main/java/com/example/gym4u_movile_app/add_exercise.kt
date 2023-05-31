package com.example.gym4u_movile_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class add_exercise : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)
        val agregarEjericio = findViewById<Button>(R.id.btn_add_exercise)
        agregarEjericio.setOnClickListener {
            val intent = Intent(this, new_workout::class.java)
            startActivity(intent)
        }
    }
}