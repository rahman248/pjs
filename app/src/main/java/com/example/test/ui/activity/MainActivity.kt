package com.example.test.ui.activity


import android.os.Bundle
import com.example.test.R
import com.example.test.base.BaseActivity
import com.example.test.databinding.ActivityMainBinding
import com.example.test.ui.AppFragmentFactory
import com.example.test.ui.fragments.LoginFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun createBinding(): ActivityMainBinding  =  ActivityMainBinding.inflate(layoutInflater)

    @Inject
    lateinit var fragmentFactory: AppFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.fragmentFactory = fragmentFactory
        }
    }

}