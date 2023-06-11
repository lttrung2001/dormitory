package com.lttrung.dormitory.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lttrung.dormitory.databinding.ActivitySuccessBinding
import com.lttrung.dormitory.ui.activities.login.LoginActivity

class SuccessActivity : AppCompatActivity() {
    private val binding: ActivitySuccessBinding by lazy {
        ActivitySuccessBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setListener()
    }

    private fun setListener() {
        binding.buttonGotoLogin.setOnClickListener {
            startActivity(Intent(this@SuccessActivity, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }
}