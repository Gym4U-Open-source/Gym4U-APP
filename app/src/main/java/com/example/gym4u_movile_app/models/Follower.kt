package com.example.gym4u_movile_app.models

import java.io.Serializable

data class Follower(
    val id: Long,
    val coachUser: FollowerUser,
    val clientUser: FollowerUser
) : Serializable