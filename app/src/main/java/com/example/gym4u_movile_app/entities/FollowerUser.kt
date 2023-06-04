package com.example.gym4u_movile_app.entities

import java.io.Serializable

data class  FollowerUser(
    val id: Long,
    val username: String,
    val email: String,
    val roles: List<Role>
) : Serializable