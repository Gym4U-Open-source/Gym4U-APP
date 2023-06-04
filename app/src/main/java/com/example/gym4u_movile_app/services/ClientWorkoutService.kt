package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.ClientWorkout
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientWorkoutService {
    @GET("api/v1/userRoutines/{clientId}")
    fun getWorkouts(@Path("clientId") clientId: Long) : Call<BaseResponse<ClientWorkout>>

    @DELETE("api/v1/userRoutines/{clientWorkoutId}")
    fun deleteClientWorkout(@Path("clientWorkoutId") clientWorkoutId: Long): Call<BaseResponse<ClientWorkout>>

}