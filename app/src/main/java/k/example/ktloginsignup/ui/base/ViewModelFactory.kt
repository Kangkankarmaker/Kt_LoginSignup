package k.example.ktloginsignup.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import k.example.ktloginsignup.data.repository.AuthRepository
import k.example.ktloginsignup.data.repository.BaseRepository
import k.example.ktloginsignup.data.repository.UserRepository
import k.example.ktloginsignup.ui.auth.AuthViewModel
import k.example.ktloginsignup.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
        private val repository:BaseRepository
):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
              modelClass.isAssignableFrom(AuthViewModel::class.java)->AuthViewModel(repository as AuthRepository) as T
              modelClass.isAssignableFrom(HomeViewModel::class.java)->HomeViewModel(repository as UserRepository) as T
             else ->throw IllegalArgumentException("viewModel class  not found")
         }
    }
}