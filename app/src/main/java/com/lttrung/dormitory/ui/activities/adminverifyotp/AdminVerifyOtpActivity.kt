package com.lttrung.dormitory.ui.activities.adminverifyotp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.detachTextChangeAnimator
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.R
import com.lttrung.dormitory.databinding.ActivityAdminVerifyOtpBinding
import com.lttrung.dormitory.ui.activities.adminhome.AdminHomeActivity
import com.lttrung.dormitory.utils.AppConstants
import com.lttrung.dormitory.utils.Resource
import com.lttrung.dormitory.utils.ValidationHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminVerifyOtpActivity : AppCompatActivity() {
    private val binding: ActivityAdminVerifyOtpBinding by lazy {
        ActivityAdminVerifyOtpBinding.inflate(layoutInflater)
    }
    private val adminVerifyOtpViewModel: AdminVerifyOtpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()
        setupObserver()
    }

    private fun setupObserver() {
        adminVerifyOtpViewModel.verifyCodeLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading
                    binding.buttonVerify.isClickable = false
                    binding.buttonVerify.showProgress {
                        buttonTextRes = R.string.loading
                        progressColor = Color.WHITE
                    }
                }
                is Resource.Success -> {
                    // Register successfully
                    binding.buttonVerify.isClickable = true
                    binding.buttonVerify.hideProgress(R.string.verify)

                    startActivity(Intent(this, AdminHomeActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                }
                is Resource.Error -> {
                    binding.buttonVerify.isClickable = true
                    binding.buttonVerify.hideProgress(R.string.verify)
                    Snackbar.make(this, binding.root, resource.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupListener() {
        binding.buttonVerify.setOnClickListener {
            val username = intent.getStringExtra(AppConstants.USERNAME) ?: ""
            val password = intent.getStringExtra(AppConstants.PASSWORD) ?: ""
            val otpCode = binding.otpCode.text.trim().toString()
            val validation = ValidationHelper()
            if (validation.isBlank(username) or validation.isBlank(password)) {
                adminVerifyOtpViewModel.verifyCodeLiveData.postValue(Resource.Error("Username and password can not be empty."))
            } else if (validation.isBlank(otpCode)) {
                adminVerifyOtpViewModel.verifyCodeLiveData.postValue(Resource.Error("OTP code can not be empty."))
            }
            if (!validation.hasError){
                adminVerifyOtpViewModel.verifyCode(username, password, otpCode)
            }
        }
    }

    private fun setupView() {
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bindProgressButton(binding.buttonVerify)
        binding.buttonVerify.detachTextChangeAnimator()
    }
}