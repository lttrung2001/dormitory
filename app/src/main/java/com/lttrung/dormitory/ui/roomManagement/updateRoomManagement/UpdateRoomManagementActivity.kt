package com.lttrung.dormitory.ui.roommanagement.updateRoomManagement

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lttrung.dormitory.databinding.ActivityUpdateRoomManagementBinding
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.ui.roommanagement.detailRoomManagement.DetailRoomManagementActivity
import com.lttrung.dormitory.utils.AppConstants
import com.lttrung.dormitory.utils.Resource

class UpdateRoomManagementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateRoomManagementBinding
    private val updateRoomManagementViewModel: UpdateRoomManagementViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupObserver()
    }

    private fun setupObserver() {
        updateRoomManagementViewModel.updateRoomManagementLiveData.observe(this) { resource: Resource<*> ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading
                }
                is Resource.Success -> {
                    val room = resource.data
                    val data = updateRoomManagementViewModel.updateRoom(room as Room)
                    val updateRoomManagementIntent =
                        Intent(this@UpdateRoomManagementActivity, DetailRoomManagementActivity::class.java)
                    updateRoomManagementIntent.putExtra(AppConstants.ROOM, data.toString())
                    startActivity(updateRoomManagementIntent)
                }
                is Resource.Error -> {
                    // Error
                }
            }
        }
    }

    private fun setupView() {
        binding = ActivityUpdateRoomManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}