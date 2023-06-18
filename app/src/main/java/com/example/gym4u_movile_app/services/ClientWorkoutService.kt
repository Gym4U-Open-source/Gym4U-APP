package com.example.gym4u_movile_app.services

import androidx.activity.result.PickVisualMediaRequest
import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.ClientWorkout
import okhttp3.RequestBody
import okhttp3.ResponseBody
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
    fun deleteClientWorkout(@Path("clientWorkoutId") clientWorkoutId: Long): Call<BaseResponse<ClientWorkout>>

}