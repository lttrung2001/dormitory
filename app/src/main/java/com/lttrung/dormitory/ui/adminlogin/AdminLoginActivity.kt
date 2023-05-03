package com.lttrung.dormitory.ui.adminlogin

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
import com.lttrung.dormitory.databinding.ActivityAdminLoginBinding
import com.lttrung.dormitory.ui.adminhome.AdminHomeActivity
import com.lttrung.dormitory.ui.verifycode.VerifyCodeActivity
import com.lttrung.dormitory.utils.AppConstants
import com.lttrung.dormitory.utils.Resource
import com.lttrung.dormitory.utils.ValidationHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminLoginActivity : AppCompatActivity() {
    private val binding: ActivityAdminLoginBinding by lazy {
        ActivityAdminLoginBinding.inflate(layoutInflater)
    }
    private val viewModel: AdminLoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.loginLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading
                    binding.buttonLogin.isClickable = false
                    binding.buttonLogin.showProgress {
                        buttonTextRes = R.string.loading
                        progressColor = Color.WHITE
                    }
                }
                is Resource.Success -> {
                    binding.buttonLogin.isClickable = true
                    binding.buttonLogin.hideProgress(R.string.login)
                    val homeIntent = Intent(this, AdminHomeActivity::class.java)
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
                                    putString(AppConstants.USERNAME, username)
                                    putString(AppConstants.PASSWORD, password)
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
                viewModel.login(username, password)
            }
        }
    }

    private fun setupView() {
        setContentView(binding.root)

        bindProgressButton(binding.buttonLogin)
        binding.buttonLogin.attachTextChangeAnimator()
    }
}