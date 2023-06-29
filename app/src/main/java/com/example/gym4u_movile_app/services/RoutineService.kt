package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.models.BaseResponse
import com.example.gym4u_movile_app.models.Routines
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RoutineService {
    @GET("api/v1/routines/{workoutId}")
    fun getRoutines(@Path("workoutId") workoutId: Int) : Call<BaseResponse<Routines>>
}