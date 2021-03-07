package k.example.ktloginsignup.ui.base

import androidx.lifecycle.ViewModel
import k.example.ktloginsignup.data.network.UserApi
import k.example.ktloginsignup.data.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel(
        private val repository: BaseRepository
):ViewModel() {

    suspend fun logout(api: UserApi)= withContext(Dispatchers.IO){repository.logout(api)}
}