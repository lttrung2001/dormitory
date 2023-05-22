package com.lttrung.dormitory.ui.adminhome

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lttrung.dormitory.databinding.ActivityAdminHomeBinding
import com.lttrung.dormitory.ui.adminlogin.AdminLoginActivity
import com.lttrung.dormitory.ui.adminviewgenderstats.AdminViewGenderStatsActivity
import com.lttrung.dormitory.ui.adminviewroomtypestats.AdminViewRoomTypeStatsActivity
import com.lttrung.dormitory.ui.adminviewstudentstats.AdminViewStudentStatsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminHomeActivity : AppCompatActivity() {
    private val binding: ActivityAdminHomeBinding by lazy {
        ActivityAdminHomeBinding.inflate(layoutInflater)
    }
    private val adminHomeViewModel: AdminHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObserver()
        setupListener()
    }

    private fun setupObserver() {
        adminHomeViewModel.logoutLiveData.observe(this) {
            startActivity(Intent(this, AdminLoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
        }
    }

    private fun setupListener() {
        binding.buttonChart1.setOnClickListener {
            startActivity(Intent(this, AdminViewStudentStatsActivity::class.java))
        }
        binding.buttonChart2.setOnClickListener {
            startActivity(Intent(this, AdminViewRoomTypeStatsActivity::class.java))
        }
        binding.buttonChart3.setOnClickListener {
            startActivity(Intent(this, AdminViewGenderStatsActivity::class.java))
        }
        binding.buttonLogout.setOnClickListener {
            adminHomeViewModel.logout()
        }
    }
}