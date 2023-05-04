package com.lttrung.dormitory.ui.roomManagement.addRoomManagement

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lttrung.dormitory.databinding.ActivityAddRoomManagementBinding
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.ui.roomManagement.RoomsManagementActivity
import com.lttrung.dormitory.utils.AppConstants
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRoomManagementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRoomManagementBinding
    private val addRoomManagementViewModel: AddRoomManagementViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupObserver()
    }

    private fun setupObserver() {
        addRoomManagementViewModel.addRoomManagementLiveData.observe(this) { resource: Resource<*> ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading
                }
                is Resource.Success -> {
                    val room = resource.data
                    val data = addRoomManagementViewModel.addRoom(room as Room)
                    val addRoomManagementIntent =
                        Intent(this@AddRoomManagementActivity, RoomsManagementActivity::class.java)
                    addRoomManagementIntent.putExtra(AppConstants.ROOM, data.toString())
                    startActivity(addRoomManagementIntent)
                }
                is Resource.Error -> {
                    // Error
                }
                else -> {}
            }
        }
    }

    private fun setupView() {
        binding = ActivityAddRoomManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}