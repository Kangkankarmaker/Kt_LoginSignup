package k.example.ktloginsignup.data.network

import k.example.ktloginsignup.data.Responses.LogInResponse
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("user")
    suspend fun getUser(    ) : LogInResponse

    @POST("logout")
    suspend fun logout() : ResponseBody
}