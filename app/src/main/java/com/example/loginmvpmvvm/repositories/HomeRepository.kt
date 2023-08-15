package com.example.loginmvpmvvm.repositories

import com.example.loginmvpmvvm.common.ResultState
import com.example.loginmvpmvvm.model.UserResponse
import com.example.loginmvpmvvm.retrofit.Api

class HomeRepositoryImpl(
    private val api: Api
): HomeRepository{

    override suspend fun getUser(idUser: String): ResultState<UserResponse?> {
        return try{
        val response = api.getUsersData(idUser = idUser)
        if (response.isSuccessful){
            ResultState.Success(response.body())
        } else {
            ResultState.Error("404", "Usuário não encontrado")
        }
        }catch (t: Throwable){
            ResultState.Failure(t)
        }
    }
}

interface HomeRepository {
    suspend fun getUser(idUser: String): ResultState<UserResponse?>
}