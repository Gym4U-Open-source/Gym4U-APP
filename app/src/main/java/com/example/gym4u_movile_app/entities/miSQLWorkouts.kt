package com.example.gym4u_movile_app.entities

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class miSQLWorkouts (context: Context) : SQLiteOpenHelper(
    context, "workouts.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val orderCreacion = "CREATE TABLE workouts" +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nameWorkout TEXT, name TEXT, account TEXT, time TEXT, assetUrl TEXT," +
                "name1 TEXT, account1 TEXT, time1 TEXT, assetUrl1 TEXT," +
                "name2 TEXT, account2 TEXT, time2 TEXT, assetUrl2 TEXT)"
        db!!.execSQL(orderCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS workouts"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun addExercises(nameWorkout: String,name: String, account: String, time: String, assetUrl: String,
                     name1: String, account1: String, time1: String, assetUrl1: String,
                     name2: String, account2: String, time2: String, assetUrl2: String){
        val newWorkout =  ContentValues()


        newWorkout.put("nameWorkout", nameWorkout)

        newWorkout.put("name", name)
        newWorkout.put("account", account)
        newWorkout.put("time", time)
        newWorkout.put("assetUrl", assetUrl)

        newWorkout.put("name1", name1)
        newWorkout.put("account1", account1)
        newWorkout.put("time1", time1)
        newWorkout.put("assetUrl1", assetUrl1)

        newWorkout.put("name2", name2)
        newWorkout.put("account2", account2)
        newWorkout.put("time2", time2)
        newWorkout.put("assetUrl2", assetUrl2)

        val db = this.writableDatabase
        db.insert("workouts", null, newWorkout)
        db.close()
    }

    fun actualizardatos(id: Int,
                        nameWorkout: String,name: String, account: String, time: String, assetUrl: String,
                        name1: String, account1: String, time1: String, assetUrl1: String,
                        name2: String, account2: String, time2: String, assetUrl2: String){
        val args = arrayOf(id.toString())

        val newWorkout =  ContentValues()

        newWorkout.put("nameWorkout", nameWorkout)

        newWorkout.put("name", name)
        newWorkout.put("account", account)
        newWorkout.put("time", time)
        newWorkout.put("assetUrl", assetUrl)

        newWorkout.put("name1", name1)
        newWorkout.put("account1", account1)
        newWorkout.put("time1", time1)
        newWorkout.put("assetUrl1", assetUrl1)

        newWorkout.put("name2", name2)
        newWorkout.put("account2", account2)
        newWorkout.put("time2", time2)
        newWorkout.put("assetUrl2", assetUrl2)

        val db = this.writableDatabase
        db.update("workouts", newWorkout,"_id = ?", args)
        db.close()
    }

}