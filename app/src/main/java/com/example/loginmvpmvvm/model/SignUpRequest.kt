package com.example.loginmvpmvvm.model

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("name") val name: String,
    @SerializedName("birthdate") val birthdate: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("type") val type: String,
    @SerializedName("status") val status: String,
    @SerializedName("password") val password: String
)