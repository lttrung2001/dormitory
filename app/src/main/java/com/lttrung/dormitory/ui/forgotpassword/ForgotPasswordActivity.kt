package com.lttrung.dormitory.ui.forgotpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lttrung.dormitory.databinding.ActivityForgotPasswordBinding
import com.lttrung.dormitory.ui.verifycode.VerifyCodeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()
    }

    private fun setupListener() {
        binding.buttonSendCode.setOnClickListener {
            startActivity(Intent(this, VerifyCodeActivity::class.java))
        }
    }

    private fun setupView() {
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}