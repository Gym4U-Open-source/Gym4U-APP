package com.example.gym4u_movile_app.models

import java.io.Serializable

class ClientWorkout (
    val id: Long?,
    val workout: WorkoutExercises,
    val client: Client,
        ) : Serializable