package com.example.gym4u_movile_app.entities

data class Library (
    val id: Long,
    val category: String,
    val approach: String,
    val name: String,
    val assetUrl: String,
    val tag: Tags
)