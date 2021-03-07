package k.example.ktloginsignup.data.repository


import k.example.ktloginsignup.data.network.UserApi

class UserRepository(
    private val api:UserApi
):BaseRepository() {

    suspend fun getUser () = safeApiCall {
        api.getUser();
    }


}