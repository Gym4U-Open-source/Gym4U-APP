package com.example.gym4u_movile_app.models.resources

import com.example.gym4u_movile_app.models.Conversation
import com.example.gym4u_movile_app.models.FollowerUser

data class MessageResource(
    val message: String,
    val conversation: Conversation,
    val user: FollowerUser
)