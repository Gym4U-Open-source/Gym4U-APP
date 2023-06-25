package com.example.gym4u_movile_app.entities

import android.provider.ContactsContract

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val roles: List<String>,
    val token: String?
)
