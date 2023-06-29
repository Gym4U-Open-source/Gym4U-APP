package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.models.BaseResponse
import com.example.gym4u_movile_app.models.Exercise
import retrofit2.Call
import retrofit2.http.GET

interface ExerciseService {
    @GET("api/v1/exercise")
    fun getAll(): Call<BaseResponse<Exercise>>
}