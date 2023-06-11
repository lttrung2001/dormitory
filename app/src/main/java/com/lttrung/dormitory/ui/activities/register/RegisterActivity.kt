package com.lttrung.dormitory.ui.activities.register

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.R
import com.lttrung.dormitory.databinding.ActivityRegisterBinding
import com.lttrung.dormitory.ui.activities.verifycode.VerifyCodeActivity
import com.lttrung.dormitory.utils.AppConstants.PASSWORD
import com.lttrung.dormitory.utils.AppConstants.USERNAME
import com.lttrung.dormitory.utils.Resource
import com.lttrung.dormitory.utils.ValidationHelper
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
                    binding.buttonRegister.isClickable = false
                    binding.buttonRegister.showProgress {
                        buttonTextRes = R.string.loading
                        progressColor = Color.WHITE
                    }
                }
                is Resource.Success -> {
                    // Do something
                    binding.buttonRegister.isClickable = true
                    binding.buttonRegister.hideProgress(R.string.register)

                    // Start verify code activity
                    val username = binding.identifier.text.trim().toString()
                    val password = binding.password.text.trim().toString()
                    startActivity(Intent(this, VerifyCodeActivity::class.java).apply {
                        val bundle = Bundle()
                        bundle.putString(USERNAME, username)
                        bundle.putString(PASSWORD, password)
                        putExtras(bundle)
                    })
                }
                is Resource.Error -> {
                    binding.buttonRegister.isClickable = true
                    binding.buttonRegister.hideProgress(R.string.register)
                    Snackbar.make(
                        this,
                        binding.linearLayout,
                        resource.message,
                        Snackbar.LENGTH_LONG
                    ).show()
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
            val validation = ValidationHelper()
            if (validation.isBlank(username)) {
                binding.identifier.error = "Username can not be empty."
            }
            if (validation.isBlank(password)) {
                binding.password.error = "Password can not be empty."
            }
            if (validation.isBlank(confirmPassword)) {
                binding.confirmPassword.error = "Confirm password can not be empty."
            }
            if (!validation.isPasswordMatched(password, confirmPassword)) {
                registerViewModel.registerLiveData.postValue(Resource.Error("Password not matched."))
            }
            if(!validation.hasError) {
                registerViewModel.register(username, password)
            }
        }
    }

    private fun setupView() {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bindProgressButton(binding.buttonRegister)
        binding.buttonRegister.attachTextChangeAnimator()
    }
}