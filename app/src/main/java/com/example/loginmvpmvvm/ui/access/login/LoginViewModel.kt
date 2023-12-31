package com.example.loginmvpmvvm.ui.access.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvpmvvm.common.ResultState
import com.example.loginmvpmvvm.model.SignInRequest
import com.example.loginmvpmvvm.model.SignInResponse
import com.example.loginmvpmvvm.repositories.AccessRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AccessRepository
) : ViewModel() {

    val errorMsg = MutableLiveData<String>()
    val signInSuccess = MutableLiveData<SignInResponse?>()
    val signInError = MutableLiveData(false)
    val notFoundError = MutableLiveData(false)
    val signInFail = MutableLiveData(false)

    fun signIn(email: String, password: String) = viewModelScope.launch {

        val signInRequest = SignInRequest(email = email, password = password)
        when (val response = repository.signIn(signInRequest)) {
            is ResultState.Success -> signInSuccess.value = response.data
            is ResultState.Error -> signInError.value = true
            is ResultState.NotFound -> notFoundError.value = true
            is ResultState.Failure -> signInFail.value = true
            else -> Unit
        }
    }
}