package com.example.loginmvpmvvm.common

sealed class ResultState<out T> {
    data class Success<out T>(val data: T): ResultState<T>()
    data class Failure<out T>(val throwable: Throwable): ResultState<T>()
    object Exists: ResultState<Nothing>()
    object Error: ResultState<Nothing>()
    object NotFound: ResultState<Nothing>()
}
