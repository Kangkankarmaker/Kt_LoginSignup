package k.example.ktloginsignup.data.repository

import k.example.ktloginsignup.data.UserPreferences
import k.example.ktloginsignup.data.network.AuthApi
import java.lang.ref.PhantomReference

class AuthRepository(
    private val api:AuthApi,
    private val preferences: UserPreferences
):BaseRepository() {

    suspend fun login(
            email: String,
            password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAuthToken(token:String){
        preferences.saveAuthToken(token)
    }
}