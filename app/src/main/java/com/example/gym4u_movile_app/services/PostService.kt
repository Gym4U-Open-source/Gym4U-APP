package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.models.BaseResponse
import com.example.gym4u_movile_app.models.Post
import com.example.gym4u_movile_app.models.resources.CreatePostResource
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PostService {
    @GET("api/v1/posts?keyword=")
    fun getAll(): Call<BaseResponse<Post>>

    @POST("api/v1/posts")
    fun createComment(@Header("Authorization") authorization: String, @Body createPostResource: CreatePostResource): Call<Post>
}