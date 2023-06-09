package com.example.gym4u_movile_app.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.gym4u_movile_app.entities.User
import com.google.gson.Gson

class AppPreferences(context: Context) {
    private val preferences = context.getSharedPreferences(NAME, MODE_PRIVATE)
    private val gson = Gson()
    companion object {
        const val NAME = "preferences"
        const val USER = "USER"

        val AppCompatActivity.preferences: AppPreferences get() = AppPreferences(this)
        val Fragment.preferences: AppPreferences get() = AppPreferences(requireContext())
    }

    private fun userToString(user: User): String = gson.toJson(user)
    private fun getUserString(): String = preferences.getString(USER, userToString(defaultUser()))!!
    private fun defaultUser(): User = User(0, "", "", listOf(), "")
    private fun editor(): SharedPreferences.Editor = preferences.edit()

    fun saveUser(user: User) {
        editor().apply {
            putString(USER, userToString(user))
            commit()
        }
    }
    fun getUser(): User = gson.fromJson(getUserString(), User::class.java)
    fun clean() = saveUser(defaultUser())
    fun isDefault() = getUser().id == 0
    fun isNotDefault() = !isDefault()
}