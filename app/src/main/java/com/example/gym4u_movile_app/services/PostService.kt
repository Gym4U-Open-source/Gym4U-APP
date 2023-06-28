package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.entities.BaseResponse
import com.example.gym4u_movile_app.entities.Comment
import com.example.gym4u_movile_app.entities.Post
import com.example.gym4u_movile_app.entities.resources.CreateCommentResource
import com.example.gym4u_movile_app.entities.resources.CreatePostResource
import com.example.gym4u_movile_app.util.ApiPaths
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PostService {
    @GET("api/v1/posts?keyword=")
    fun getAll(): Call<BaseResponse<Post>>

    @POST("api/v1/posts")
    fun createComment(@Header("Authorization") authorization: String, @Body createPostResource: CreatePostResource): Call<Post>
}