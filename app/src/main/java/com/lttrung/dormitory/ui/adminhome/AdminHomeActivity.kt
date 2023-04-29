package com.lttrung.dormitory.ui.adminhome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lttrung.dormitory.databinding.ActivityAdminHomeBinding

class AdminHomeActivity : AppCompatActivity() {
    private val binding: ActivityAdminHomeBinding by lazy {
        ActivityAdminHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(binding.root)
    }
}