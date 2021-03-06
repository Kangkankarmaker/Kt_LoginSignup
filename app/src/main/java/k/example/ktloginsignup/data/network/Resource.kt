package k.example.ktloginsignup.data.network

import okhttp3.ResponseBody

sealed class Resource <out T>{
    data class Success<out T>(val value: T):Resource<T>()
    data class Failure(
        val isNetwork :Boolean,
        val errorCode : Int?,
        val errorBody : ResponseBody?
    ):Resource<Nothing>()
}