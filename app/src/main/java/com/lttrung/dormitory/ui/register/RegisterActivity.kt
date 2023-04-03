package com.lttrung.dormitory.ui.register

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.lttrung.dormitory.databinding.ActivityRegisterBinding
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()
        setupObserver()
    }

    private fun setupObserver() {
        registerViewModel.registerLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading
                }
                is Resource.Success -> {
                    // Do something
                }
                is Resource.Error -> {
                    binding.error.text = resource.data ?: "Unknown error"
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupListener() {
        binding.buttonRegister.setOnClickListener {
            val username = binding.identifier.text.trim().toString()
            val password = binding.password.text.trim().toString()
            val confirmPassword = binding.confirmPassword.text.trim().toString()
            if (password != confirmPassword) {
                binding.password.error = ""
                binding.confirmPassword.error = ""
                binding.error.text = "Password not matched."
            } else {
                binding.password.error = null
                binding.confirmPassword.error = null
                binding.error.text = ""

                registerViewModel.register(username, password)
            }
        }
    }

    private fun setupView() {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}