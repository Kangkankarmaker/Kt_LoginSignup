package k.example.ktloginsignup.data.network

import k.example.ktloginsignup.data.Responses.LogInResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {


    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email")  email:String,
        @Field("password")  password:String
    ) : LogInResponse
}