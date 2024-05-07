package me.nasiri.coinmaster.domain.util

sealed class Resource<T>(val data: T? = null, val message: Errors? = null) {
    class Success<T>(data: T) : Resource<T>(data = data, message = null)
    class Error<T>(message: Errors?, data: T? = null) : Resource<T>(data = data, message = message)

}


enum class Errors {
    NETWORK,
    FOUND,
    EMPTY,
    OTHER
}