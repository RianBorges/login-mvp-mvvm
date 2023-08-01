package com.example.loginmvpmvvm.ui.access.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvpmvvm.common.ResultState
import com.example.loginmvpmvvm.model.SignUpRequest
import com.example.loginmvpmvvm.model.SignUpResponse
import com.example.loginmvpmvvm.repositories.AccessRepository
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: AccessRepository): ViewModel() {

    val errorMsg = MutableLiveData<String>()
    val signUpSuccess = MutableLiveData<SignUpResponse?>()
    val signUpError = MutableLiveData(false)
    val signUpFail = MutableLiveData(false)
    val signUpExists = MutableLiveData(false)

    fun signUp(name: String, birthdate: String, email: String, phone: String, type: String,
               status: String, password: String) = viewModelScope.launch {

        val signUpRequest = SignUpRequest(name, birthdate, email, phone, type, status, password)
        when (val response = repository.register(signUpRequest)) {
                is ResultState.Success ->  signUpSuccess.value = response.data
                is ResultState.Error ->  signUpError.value = true
            is ResultState.Failure ->  signUpFail.value = true
            is ResultState.Exists ->  signUpExists.value = true
        }
    }
}