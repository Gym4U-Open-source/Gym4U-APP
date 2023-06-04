package com.example.gym4u_movile_app.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Client (

    val id: Long,
    val lastName: String,
    val name: String,
    val userId: Long

    ) : Serializable