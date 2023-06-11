package com.lttrung.dormitory.ui.activities.forgotpassword

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.R
import com.lttrung.dormitory.databinding.ActivityForgotPasswordBinding
import com.lttrung.dormitory.ui.activities.SuccessActivity
import com.lttrung.dormitory.utils.Resource
import com.lttrung.dormitory.utils.ValidationHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private val forgotPasswordViewModel: ForgotPasswordViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()
        setupObserver()
    }

    private fun setupObserver() {
        forgotPasswordViewModel.forgotPasswordLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.buttonResetPassword.isClickable = false
                    binding.buttonResetPassword.showProgress {
                        buttonTextRes = R.string.loading
                        progressColor = Color.WHITE
                    }
                }
                is Resource.Success -> {
                    binding.buttonResetPassword.isClickable = true
                    binding.buttonResetPassword.hideProgress(R.string.reset_password)
                    startActivity(Intent(this@ForgotPasswordActivity, SuccessActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                }
                is Resource.Error -> {
                    binding.buttonResetPassword.isClickable = true
                    binding.buttonResetPassword.hideProgress(R.string.reset_password)
                    Snackbar.make(
                        this@ForgotPasswordActivity,
                        binding.root,
                        resource.message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setupListener() {
        binding.buttonResetPassword.setOnClickListener {
            val username = binding.identifier.text.trim().toString()
            val helper = ValidationHelper()
            if (helper.isBlank(username)) {
                Snackbar.make(
                    this@ForgotPasswordActivity,
                    binding.root,
                    "Username can not be empty.",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            if (!helper.hasError) {
                forgotPasswordViewModel.forgotPassword(username)
            }
        }
    }

    private fun setupView() {
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}