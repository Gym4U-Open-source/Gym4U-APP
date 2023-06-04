package com.example.gym4u_movile_app.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {
        fun build(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(ApiPaths.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}