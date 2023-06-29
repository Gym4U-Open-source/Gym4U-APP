package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.models.BaseResponse
import com.example.gym4u_movile_app.models.Comment
import com.example.gym4u_movile_app.models.resources.CreateCommentResource
import com.example.gym4u_movile_app.util.ApiPaths
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentService {
    @GET("${ApiPaths.COMMENTS}post/{postId}")
    fun getComments(@Path("postId") postId: Long): Call<BaseResponse<Comment>>

    @POST(ApiPaths.COMMENTS)
    fun createComment(@Header("Authorization") authorization: String, @Body createCommentResource: CreateCommentResource): Call<Comment>
}