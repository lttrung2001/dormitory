package com.lttrung.dormitory.ui.changepassword

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
import com.lttrung.dormitory.databinding.ActivityChangePasswordBinding
import com.lttrung.dormitory.utils.Resource
import com.lttrung.dormitory.utils.ValidationHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordActivity : AppCompatActivity() {
    private val binding: ActivityChangePasswordBinding by lazy {
        ActivityChangePasswordBinding.inflate(layoutInflater)
    }
    private val changePasswordViewModel: ChangePasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
        setupListener()
        setupObserver()
    }

    private fun setupView() {
        bindProgressButton(binding.buttonChangePassword)
        binding.buttonChangePassword.attachTextChangeAnimator()
    }

    private fun setupObserver() {
        changePasswordViewModel.changePasswordLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading
                    binding.buttonChangePassword.isClickable = false
                    binding.buttonChangePassword.showProgress {
                        buttonTextRes = R.string.loading
                        progressColor = Color.WHITE
                    }
                }
                is Resource.Success -> {
                    // Register successfully
                    binding.buttonChangePassword.isClickable = true
                    binding.buttonChangePassword.hideProgress(R.string.change_password)
                    finish()
                }
                is Resource.Error -> {
                    binding.buttonChangePassword.isClickable = true
                    binding.buttonChangePassword.hideProgress(R.string.change_password)
                    Snackbar.make(this, binding.root, resource.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupListener() {
        binding.buttonChangePassword.setOnClickListener {
            val currentPassword = binding.currentPassword.text.trim().toString()
            val newPassword = binding.newPassword.text.trim().toString()
            val confirmPassword = binding.confirmPassword.text.trim().toString()
            val helper = ValidationHelper()
            if (helper.isBlank(currentPassword)) {
                binding.currentPassword.error = "Current password can not be empty."
            }
            if (helper.isBlank(newPassword)) {
                binding.newPassword.error = "New password can not be empty."
            }
            if (helper.isBlank(confirmPassword)) {
                binding.confirmPassword.error = "Confirm password can not be empty."
            }
            if (!helper.isPasswordMatched(newPassword, confirmPassword)) {
                Snackbar.make(
                    this@ChangePasswordActivity,
                    binding.root,
                    "Password not matched.",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            if (!helper.hasError) {
                changePasswordViewModel.changePassword(currentPassword, newPassword)
            }
        }
    }
}