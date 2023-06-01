package com.example.gym4u_movile_app.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {
        fun build(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://gym4u-api-388317.rj.r.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}