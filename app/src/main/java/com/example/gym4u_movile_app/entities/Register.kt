package com.example.gym4u_movile_app.entities

data class Register(
    val username: String,
    val password: String,
    val email: String,
    val roles: List<String>
)
