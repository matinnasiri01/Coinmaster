package me.nasiri.coinmaster.domain.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data = data, message = null)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data = data, message = message)

}