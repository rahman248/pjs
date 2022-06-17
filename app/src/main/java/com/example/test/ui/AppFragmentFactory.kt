package com.example.test.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.test.data.UserRepositoryImpl
import com.example.test.ui.fragments.HomeFragment
import com.example.test.ui.fragments.LoginFragment
import com.example.test.ui.viewmodels.HomeViewModel
import com.example.test.ui.viewmodels.LoginViewModel
import javax.inject.Inject

class AppFragmentFactory  @Inject constructor(
    private val usersRepositoryImpl: UserRepositoryImpl) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            LoginFragment::class.java.name -> LoginFragment(
                LoginViewModel(usersRepositoryImpl)
            )

            HomeFragment::class.java.name -> HomeFragment(
                HomeViewModel(usersRepositoryImpl)
            )

            else -> super.instantiate(classLoader, className)
        }
    }
}