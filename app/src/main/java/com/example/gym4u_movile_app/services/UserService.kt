package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.models.Login
import com.example.gym4u_movile_app.models.LoginResponse
import com.example.gym4u_movile_app.models.Register
import com.example.gym4u_movile_app.models.User
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