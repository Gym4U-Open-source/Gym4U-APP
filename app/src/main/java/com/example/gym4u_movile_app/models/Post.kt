package com.example.gym4u_movile_app.models

data class Post(
    val id: Long,
    val title: String?,
    val description: String?,
    val urlImage: String?,
    val comments: List<Comment>?,
    //@SerializedName("user")
    val user: User?
)