package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.models.BaseResponse
import com.example.gym4u_movile_app.models.ClientWorkout
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClientWorkoutService {
    @GET("api/v1/userRoutines/{clientId}")
    fun getWorkouts(@Path("clientId") clientId: Long) : Call<BaseResponse<ClientWorkout>>

    @GET("api/v1/userRoutines")
    fun getAllWorkouts() : Call<BaseResponse<ClientWorkout>>

    @POST("api/v1/userRoutines")
    fun postUserWorkout(@Body requestBody: ClientWorkout): Call<Void>

    @DELETE("api/v1/userRoutines/{clientWorkoutId}")
    fun deleteClientWorkout(@Path("clientWorkoutId") clientWorkoutId: Long): Call<Void>

}