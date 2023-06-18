package com.example.gym4u_movile_app.util

class ApiPaths {
    companion object {
        const val BASE_URL = "http://192.168.18.26:8080"
        private const val API_VERSION = "api/v1/"
        const val USERS = "${API_VERSION}users/"
        const val CONVERSATIONS = "${API_VERSION}conversations/"
        const val MESSAGES = "${API_VERSION}messages/"
        const val LOGIN = "${API_VERSION}users/auth/sign-in"
    }
}