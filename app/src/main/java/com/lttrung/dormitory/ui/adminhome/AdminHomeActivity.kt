package com.lttrung.dormitory.ui.adminhome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lttrung.dormitory.databinding.ActivityAdminHomeBinding
import com.lttrung.dormitory.ui.adminviewroomtypestats.AdminViewRoomTypeStatsActivity
import com.lttrung.dormitory.ui.adminviewstudentstats.AdminViewStudentStatsActivity

class AdminHomeActivity : AppCompatActivity() {
    private val binding: ActivityAdminHomeBinding by lazy {
        ActivityAdminHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListener()
    }

    private fun setupListener() {
        binding.buttonChart1.setOnClickListener {
            startActivity(Intent(this, AdminViewStudentStatsActivity::class.java))
        }
        binding.buttonChart2.setOnClickListener {
            startActivity(Intent(this, AdminViewRoomTypeStatsActivity::class.java))
        }
    }
}