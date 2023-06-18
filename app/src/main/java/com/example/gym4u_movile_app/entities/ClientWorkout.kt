package com.example.gym4u_movile_app.entities

import java.io.Serializable

class ClientWorkout (
    val id: Long?,
    val workout: WORKOUTT,
    val client: Client,
        ) : Serializable