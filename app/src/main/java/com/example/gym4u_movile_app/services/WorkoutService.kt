package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.WORKOUTT
import retrofit2.Call;
import retrofit2.http.GET;

interface WorkoutService {
    @GET("api/v1/workout")
    fun getAll(): Call<BaseResponse<WORKOUTT>>
}