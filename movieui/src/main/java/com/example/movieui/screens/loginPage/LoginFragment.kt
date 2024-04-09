package com.example.movieui.screens.loginPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.utils.ViewModelFactory
import com.example.movieui.R
import com.example.movieui.databinding.FragmentLoginPageBinding

import dagger.android.support.DaggerFragment
import javax.inject.Inject


class LoginFragment : DaggerFragment(R.layout.fragment_login_page) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }
    private val binding by viewBinding(FragmentLoginPageBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonLogin.setOnClickListener {
            viewModel.errorMsg.observe(viewLifecycleOwner) { text ->
                binding.tvError.text = text
            }
            viewModel.authentication(
                login = binding.tvLogin.text.toString(),
                password = binding.tvPassword.text.toString()
            )
        }
    }
}

