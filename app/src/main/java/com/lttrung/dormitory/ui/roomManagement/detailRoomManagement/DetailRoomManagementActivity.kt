package com.lttrung.dormitory.ui.roomManagement.detailRoomManagement

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lttrung.dormitory.databinding.ActivityDetailRoomManagementBinding
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.ui.roomManagement.updateRoomManagement.UpdateRoomManagementActivity
import com.lttrung.dormitory.utils.AppConstants
import com.lttrung.dormitory.utils.Resource


class DetailRoomManagementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailRoomManagementBinding
    private val detailRoomManagementViewModel: DetailRoomManagementViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupObserver()
    }

    private fun setupObserver() {
        detailRoomManagementViewModel.deleteRoomManagementLiveData.observe(this) { resource: Resource<*> ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading
                }
                is Resource.Success -> {
                    val room = resource.data
                    val data = detailRoomManagementViewModel.deleteRoom(room as Room)
                    val deleteRoomManagementIntent =
                        Intent(
                            this@DetailRoomManagementActivity,
                            UpdateRoomManagementActivity::class.java
                        )
                    deleteRoomManagementIntent.putExtra(AppConstants.ROOM, data.toString())
                    startActivity(deleteRoomManagementIntent)
                }
                is Resource.Error -> {
                    // Error
                }
                else -> {}
            }
        }
    }

    private fun setupView() {
        binding = ActivityDetailRoomManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}