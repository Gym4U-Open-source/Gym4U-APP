package com.example.gym4u_movile_app.entities.resources

import com.example.gym4u_movile_app.entities.Conversation
import com.example.gym4u_movile_app.entities.FollowerUser

data class MessageResource(
    val message: String,
    val conversation: Conversation,
    val user: FollowerUser
)