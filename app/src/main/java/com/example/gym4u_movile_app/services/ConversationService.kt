package com.example.gym4u_movile_app.services

import com.example.gym4u_movile_app.models.Conversation
import com.example.gym4u_movile_app.models.resources.ConversationResource
import com.example.gym4u_movile_app.util.ApiPaths
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ConversationService {
    @GET("${ApiPaths.CONVERSATIONS}coach/{coachId}/client/{clientId}")
    fun getConversation(@Path("coachId") coachId: Long, @Path("clientId") clientId: Long): Call<Conversation>

    @POST(ApiPaths.CONVERSATIONS)
    fun createConversation(@Body conversationResource: ConversationResource): Call<Conversation>
}