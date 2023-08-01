package com.example.loginmvpmvvm.repositories

import com.example.loginmvpmvvm.common.ResultState
import com.example.loginmvpmvvm.model.SignInRequest
import com.example.loginmvpmvvm.model.SignInResponse
import com.example.loginmvpmvvm.model.SignUpRequest
import com.example.loginmvpmvvm.model.SignUpResponse
import com.example.loginmvpmvvm.retrofit.Api

class AccessRepositoryImpl(
    private val api: Api
) : AccessRepository {

    override suspend fun signIn(body: SignInRequest): ResultState<SignInResponse> {
        return try {
            val response = api.signIn(body)
            if (response.isSuccessful) {
                ResultState.Success(response.body()!!)
            } else {
                ResultState.Error("404", "Usuário ou senha inválida")
            }
        } catch (t: Throwable) {
            ResultState.Failure(t)
        }
    }

    override suspend fun register(body: SignUpRequest): ResultState<SignUpResponse> {
        return try {
            val response = api.signUp(body)
            if (response.code() == 201){
                ResultState.Success(response.body()!!)
            } else if (response.code() == 409){
                ResultState.Exists
            } else {
                ResultState.Error("404", "Usuário ou senha inválida")
            }
        } catch (t: Throwable) {
            ResultState.Failure(t)
        }
    }

}

interface AccessRepository {
    suspend fun signIn(body: SignInRequest): ResultState<SignInResponse>
    suspend fun register(body: SignUpRequest): ResultState<SignUpResponse>
}
