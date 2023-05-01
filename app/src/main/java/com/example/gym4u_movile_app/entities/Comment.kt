package com.example.gym4u_movile_app.entities

data class Comment(
    val id: Long,
    val review: String,
    val user: User,
    val post: Post? = null

)