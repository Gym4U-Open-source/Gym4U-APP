package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.entities.Login
import com.example.gym4u_movile_app.entities.LoginResponse
import com.example.gym4u_movile_app.entities.Register
import com.example.gym4u_movile_app.entities.User
import com.example.gym4u_movile_app.util.ApiPaths
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call

interface UserService {
    @POST(ApiPaths.LOGIN)
    fun login(@Body login: Login): Call<LoginResponse>
    @POST(ApiPaths.REGISTER)
    fun register(@Body register: Register): Call<User>
}