package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Client
import retrofit2.Call
import retrofit2.http.GET

interface ClientService {
    @GET("api/v1/profiles")
    fun getClients() : Call<BaseResponse<Client>>
}