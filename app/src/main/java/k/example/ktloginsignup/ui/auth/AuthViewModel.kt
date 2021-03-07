package k.example.ktloginsignup.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import k.example.ktloginsignup.data.Responses.LogInResponse
import k.example.ktloginsignup.data.network.Resource
import k.example.ktloginsignup.data.repository.AuthRepository
import k.example.ktloginsignup.ui.base.BaseViewModel
import kotlinx.coroutines.launch


class AuthViewModel(
        private val repository: AuthRepository
) : BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Resource<LogInResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LogInResponse>>
        get() = _loginResponse

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginResponse.value=Resource.Loading
        _loginResponse.value = repository.login(email, password)
    }

    suspend fun saveAuthToken(token:String){
        repository.saveAuthToken(token)
    }
}