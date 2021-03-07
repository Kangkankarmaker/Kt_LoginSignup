package k.example.ktloginsignup.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import k.example.ktloginsignup.data.Responses.LogInResponse
import k.example.ktloginsignup.data.network.Resource
import k.example.ktloginsignup.data.repository.UserRepository
import k.example.ktloginsignup.ui.base.BaseViewModel
import kotlinx.coroutines.launch


class HomeViewModel(
        private val repository: UserRepository
): BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<LogInResponse>> = MutableLiveData()
    val user: LiveData<Resource<LogInResponse>>
       get() = _user

    fun getUser()=viewModelScope.launch {
        _user.value=Resource.Loading
        _user.value=repository.getUser()
    }

}