package com.example.loginmvpmvvm.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SignUpResponse(
    @SerializedName("ROWID") val idUser: String
): Serializable

