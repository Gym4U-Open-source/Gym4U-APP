package com.example.gym4u_movile_app.entities

import com.google.gson.annotations.SerializedName

class BaseResponse<T> (
    val content: List<T>
)