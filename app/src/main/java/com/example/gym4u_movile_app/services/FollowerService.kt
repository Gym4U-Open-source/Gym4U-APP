package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Follower
import com.example.gym4u_movile_app.util.ApiPaths
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FollowerService {
    @GET("${ApiPaths.USERS}{coachId}/followers")
    fun getFollowers(@Path("coachId") coachId: Long): Call<BaseResponse<Follower>>
}