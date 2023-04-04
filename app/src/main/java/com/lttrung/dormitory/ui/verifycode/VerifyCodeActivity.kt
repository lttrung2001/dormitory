package com.lttrung.dormitory.ui.verifycode

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.detachTextChangeAnimator
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.R
import com.lttrung.dormitory.databinding.ActivityVerifyCodeBinding
import com.lttrung.dormitory.utils.AppConstants.PASSWORD
import com.lttrung.dormitory.utils.AppConstants.USERNAME
import com.lttrung.dormitory.utils.Resource
import com.lttrung.dormitory.utils.ValidationHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyCodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerifyCodeBinding
    private val verifyCodeViewModel: VerifyCodeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()
        setupObserver()
    }

    private fun setupObserver() {
        verifyCodeViewModel.verifyCodeLiveData.observe(this) { resource ->
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
            val username = intent.getStringExtra(USERNAME) ?: ""
            val password = intent.getStringExtra(PASSWORD) ?: ""
            val otpCode = binding.otpCode.text.trim().toString()
            val validation = ValidationHelper
            if (validation.isBlank(username) or validation.isBlank(password)) {
                verifyCodeViewModel.verifyCodeLiveData.postValue(Resource.Error("Username and password can not be empty."))
            } else if (validation.isBlank(otpCode)) {
                verifyCodeViewModel.verifyCodeLiveData.postValue(Resource.Error("OTP code can not be empty."))
            }
            if (!validation.hasError){
                verifyCodeViewModel.verifyCode(username, password, otpCode)
            }
        }
    }

    private fun setupView() {
        binding = ActivityVerifyCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bindProgressButton(binding.buttonVerify)
        binding.buttonVerify.detachTextChangeAnimator()
    }
}