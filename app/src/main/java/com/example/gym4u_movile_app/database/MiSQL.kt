package com.example.gym4u_movile_app.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class miSQL(context: Context) : SQLiteOpenHelper(
    context, "exercises.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val orderCreacion = "CREATE TABLE exercises" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT, account TEXT, time TEXT, assetUrl TEXT)"
        db!!.execSQL(orderCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS exercises"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun addExercises(name: String, account: String, time: String, assetUrl: String){
        val exercise =  ContentValues()
        exercise.put("name", name)
        exercise.put("account", account)
        exercise.put("time", time)
        exercise.put("assetUrl", assetUrl)

        val db = this.writableDatabase
        db.insert("exercises", null, exercise)
        db.close()
    }

}