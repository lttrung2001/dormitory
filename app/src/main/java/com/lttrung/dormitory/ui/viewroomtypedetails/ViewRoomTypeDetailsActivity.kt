package com.lttrung.dormitory.ui.viewroomtypedetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lttrung.dormitory.databinding.ActivityViewRoomTypeDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewRoomTypeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewRoomTypeDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding = ActivityViewRoomTypeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}