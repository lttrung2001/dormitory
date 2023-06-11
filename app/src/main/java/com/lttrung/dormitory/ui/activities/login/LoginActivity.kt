package com.lttrung.dormitory.ui.activities.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.R
import com.lttrung.dormitory.databinding.ActivityLoginBinding
import com.lttrung.dormitory.ui.activities.adminlogin.AdminLoginActivity
import com.lttrung.dormitory.ui.activities.forgotpassword.ForgotPasswordActivity
import com.lttrung.dormitory.ui.activities.main.MainActivity
import com.lttrung.dormitory.ui.activities.register.RegisterActivity
import com.lttrung.dormitory.ui.activities.verifycode.VerifyCodeActivity
import com.lttrung.dormitory.utils.AppConstants.PASSWORD
import com.lttrung.dormitory.utils.AppConstants.USERNAME
import com.lttrung.dormitory.utils.Resource
import com.lttrung.dormitory.utils.ValidationHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val loginViewModel: LoginViewModel by viewModels()

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
                    // Loading
                    binding.buttonLogin.isClickable = false
                    binding.buttonLogin.showProgress {
                        buttonTextRes =R.string.loading
                        progressColor = Color.WHITE
                    }
                }
                is Resource.Success -> {
                    binding.buttonLogin.isClickable = true
                    binding.buttonLogin.hideProgress(R.string.login)
                    val homeIntent = Intent(this, MainActivity::class.java)
                    homeIntent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(homeIntent)
                }
                is Resource.Error -> {
                    binding.buttonLogin.isClickable = true
                    binding.buttonLogin.hideProgress(R.string.login)
                    when (resource.message) {
                        "Unverified email" -> {
                            val username = binding.identifier.text.trim().toString()
                            val password = binding.identifier.text.trim().toString()
                            startActivity(Intent(this, VerifyCodeActivity::class.java).apply {
                                val verifyCodeBundle = Bundle().apply {
                                    putString(USERNAME, username)
                                    putString(PASSWORD, password)
                                }
                                putExtras(verifyCodeBundle)
                            })
                        }
                        else -> {
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
        }
    }

    private fun setupListener() {
        binding.buttonLogin.setOnClickListener {
            val username = binding.identifier.text.toString()
            val password = binding.password.text.toString()
            val validation = ValidationHelper()
            if (validation.isBlank(username)) {
                binding.identifier.error = "Username can not be empty."
            }
            if (validation.isBlank(password)) {
                binding.password.error = "Password can not be empty."
            }
            if (!validation.hasError) {
                loginViewModel.login(username, password)
            }
        }
        binding.buttonRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.buttonForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        binding.buttonManagerLogin.setOnClickListener {
            startActivity(Intent(this, AdminLoginActivity::class.java))
        }
    }

    private fun setupView() {
        setContentView(binding.root)

        bindProgressButton(binding.buttonLogin)
        binding.buttonLogin.attachTextChangeAnimator()
    }
}