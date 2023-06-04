package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Message
import com.example.gym4u_movile_app.entities.resources.MessageResource
import com.example.gym4u_movile_app.util.ApiPaths
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface MessageService {
    @GET("${ApiPaths.MESSAGES}{messageId}")
    fun getMessage(@Path("messageId") messageId: Int): Call<BaseResponse<Message>>

    @GET(ApiPaths.MESSAGES)
    fun getAll(): Call<BaseResponse<Message>>

    @POST(ApiPaths.MESSAGES)
    fun createMessage(@Body messageResource: MessageResource): Call<Message>
}