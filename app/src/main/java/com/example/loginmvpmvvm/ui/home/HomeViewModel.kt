package com.example.loginmvpmvvm.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvpmvvm.common.ResultState
import com.example.loginmvpmvvm.model.UserResponse
import com.example.loginmvpmvvm.repositories.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {
    val userData = MutableLiveData<UserResponse?>()

    fun getUser(idUser: String) = viewModelScope.launch {
        when (val response = repository.getUser(idUser = idUser)) {
            is ResultState.Success -> userData.value = response.data
            is ResultState.Error -> userData.value = null
            is ResultState.Failure -> userData.value = null
            else -> Unit
        }
    }
}