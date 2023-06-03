package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Library
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LibraryService {
    @GET("api/v1/exercise")
    fun getAll(): Call<BaseResponse<Library>>

    @POST("api/v1/exercise")
    fun addData(newData:Library): Call<BaseResponse<Library>>
}