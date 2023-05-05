package com.lttrung.dormitory.ui.roommanagement

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lttrung.dormitory.databinding.ActivityRoomsManagementBinding
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.data.network.models.RoomType
import com.lttrung.dormitory.ui.adapters.RoomAdapter
import com.lttrung.dormitory.ui.roommanagement.detailRoomManagement.DetailRoomManagementActivity
import com.lttrung.dormitory.utils.AppConstants.ROOM
import com.lttrung.dormitory.utils.AppConstants.ROOM_TYPE
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class RoomsManagementActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomsManagementBinding
    private val roomsManagementViewModel: RoomsManagementViewModel by viewModels()
    private val roomAdapter: RoomAdapter by lazy {
        RoomAdapter { viewBinding, room ->
            // View room details
            val viewRoomManagementDetailIntent =
                Intent(this@RoomsManagementActivity, DetailRoomManagementActivity::class.java)
            val roomType = intent.getSerializableExtra(ROOM_TYPE) as RoomType
            viewRoomManagementDetailIntent.putExtra(ROOM_TYPE, roomType)
            viewRoomManagementDetailIntent.putExtra(ROOM, room)
            startActivity(viewRoomManagementDetailIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupObserver()
        fetchData()
    }

    private fun fetchData() {
        val roomType = intent.getSerializableExtra(ROOM_TYPE) as RoomType
        roomsManagementViewModel.getRooms(roomType.id)
    }

    private fun setupObserver() {
        roomsManagementViewModel.roomsManagementLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading
                }
                is Resource.Success -> {
                    val rooms = resource.data
//                    change here
                    roomAdapter.submitList(rooms)
                }
                is Resource.Error -> {
                    // Error
                }
            }
        }
    }

    private fun setupView() {
        binding = ActivityRoomsManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcvRooms.apply {
            layoutManager = LinearLayoutManager(this@RoomsManagementActivity)
            adapter = roomAdapter
        }
        roomAdapter.submitList(createSomeRooms())
    }

    private fun createSomeRooms(): MutableList<Room> {
        val data = mutableListOf<Room>()
        for (i in 1 until 20) {
            data.add(
                Room(
                    i,
                    "Room name $i",
                    Random.nextInt(1, 1000).toDouble(),
                    Random.nextInt(1, 10),
                    "",
                    "No description"
                )
            )
        }
        return data
    }
}