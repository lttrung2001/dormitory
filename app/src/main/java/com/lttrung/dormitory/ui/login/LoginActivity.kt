package com.lttrung.dormitory.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.databinding.ActivityLoginBinding
import com.lttrung.dormitory.ui.main.MainActivity
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()
        setupObserver()
    }

    private fun setupObserver() {
        loginViewModel.loginLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val homeIntent = Intent(this, MainActivity::class.java)
                    homeIntent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(homeIntent)
                }
                is Resource.Error -> {
                    Snackbar.make(this, binding.root, resource.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupListener() {
        binding.buttonLogin.setOnClickListener {
            val username = binding.email.text.toString()
            val password = binding.password.text.toString()
            loginViewModel.login(username, password)
        }
    }

    private fun setupView() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}