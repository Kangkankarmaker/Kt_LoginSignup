package k.example.ktloginsignup.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import k.example.ktloginsignup.databinding.FragmentLogInBinding
import k.example.ktloginsignup.data.network.AuthApi
import k.example.ktloginsignup.data.network.Resource
import k.example.ktloginsignup.data.repository.AuthRepository
import k.example.ktloginsignup.ui.base.BaseFragment
import k.example.ktloginsignup.ui.enable
import k.example.ktloginsignup.ui.home.HomeActivity
import k.example.ktloginsignup.ui.startNewActivity
import k.example.ktloginsignup.ui.visible
import kotlinx.coroutines.launch


class LogInFragment : BaseFragment<AuthViewModel,FragmentLogInBinding,AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressBar.visible(false)
        binding.buttonLogIn.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visible(false)
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "done", Toast.LENGTH_SHORT).show()

                    viewModel.saveAuthToken(it.value.user.access_token.toString())
                    requireActivity().startNewActivity(HomeActivity::class.java)


                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "not done", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.txtLogInPassword.addTextChangedListener {
            val email=binding.txtLogInEmail.text.toString().trim()
            binding.buttonLogIn.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogIn.setOnClickListener {
            val email=binding.txtLogInEmail.text.toString().trim()
            val password=binding.txtLogInPassword.text.toString().trim()

            viewModel.login(email, password)
            binding.progressBar.visible(true)
        }
    }


    override fun getViewModel()= AuthViewModel::class.java

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?)= FragmentLogInBinding.inflate(inflater,container,false)

    override fun getFragmentRepository()= AuthRepository(remoteDataSource.buildApi(AuthApi::class.java),userPreferences)


}