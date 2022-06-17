package com.example.test.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.base.BaseFragment
import com.example.test.databinding.FragmentLoginBinding
import com.example.test.ui.viewmodels.LoginViewModel
import com.example.test.utils.CustomProgressDialog
import com.example.test.utils.EnumStatus

class LoginFragment(
    viewModelTestFragment: LoginViewModel? = null
) : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    R.layout.fragment_login,
    LoginViewModel::class.java,
    viewModelTestFragment
) {
    override fun getViewBinding() = FragmentLoginBinding.inflate(layoutInflater)

    private val progressDialog by lazy { CustomProgressDialog(requireActivity()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.users.observe(viewLifecycleOwner) { retVal ->
            when (retVal.status) {
                EnumStatus.SUCCESS -> {
                    if (retVal.data == null) {

                        return@observe
                    } else {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }


                }
                EnumStatus.LOADING ->{}
                EnumStatus.ERROR ->{}
            }

        }

        initClickListener()
    }

    private fun initClickListener() {
        val username  = binding.editTextEmail.text
        val pass = binding.editTextPassword.text

        binding.cirLoginButton.setOnClickListener {
            progressDialog.start("Please Wait...")
            if (username!!.isEmpty() || pass!!.isEmpty()){
                Toast.makeText(requireContext(), "Please check your fields!", Toast.LENGTH_LONG).show()
            } else {

                viewModel.fetchLoginApi(username.toString(), pass.toString())
                Handler(Looper.getMainLooper()).postDelayed({
                    // Dismiss progress bar after 4 seconds
                    progressDialog.stop()
                }, 4000)

            }
        }


    }

}