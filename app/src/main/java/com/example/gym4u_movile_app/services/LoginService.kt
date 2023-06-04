package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.entities.Login
import com.example.gym4u_movile_app.entities.LoginResponse
import com.example.gym4u_movile_app.util.ApiPaths
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface LoginService {
    @POST(ApiPaths.LOGIN)
    fun login(@Body login: Login): Call<LoginResponse>
}