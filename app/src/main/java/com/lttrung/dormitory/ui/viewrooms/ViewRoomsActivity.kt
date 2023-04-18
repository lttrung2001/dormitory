package com.lttrung.dormitory.ui.viewrooms

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import com.lttrung.dormitory.R
import com.lttrung.dormitory.databinding.ActivityViewRoomsBinding
import com.lttrung.dormitory.databinding.LayoutRoomBinding
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.data.network.models.RoomType
import com.lttrung.dormitory.ui.adapters.RoomAdapter
import com.lttrung.dormitory.ui.registerroom.RegisterRoomActivity
import com.lttrung.dormitory.utils.AppConstants.ROOM
import com.lttrung.dormitory.utils.AppConstants.ROOM_TYPE
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewRoomsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewRoomsBinding
    private val viewRoomsViewModel: ViewRoomsViewModel by viewModels()
    private val roomAdapter: RoomAdapter by lazy {
        RoomAdapter(object : RoomAdapter.ItemListener {
            override fun onClick(viewBinding: LayoutRoomBinding, room: Room) {
                // View room details
                val viewRoomDetailsIntent =
                    Intent(this@ViewRoomsActivity, RegisterRoomActivity::class.java)
                val roomType = intent.getSerializableExtra(ROOM_TYPE) as RoomType
                viewRoomDetailsIntent.putExtra(ROOM_TYPE, roomType)
                viewRoomDetailsIntent.putExtra(ROOM, room)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@ViewRoomsActivity,
                    Pair(viewBinding.roomImage, getString(R.string.room_image_transition)),
                    Pair(
                        viewBinding.roomId, getString(R.string.room_name_transition),
                    ),
                    Pair(viewBinding.roomPrice, getString(R.string.room_price_transition)),
                    Pair(
                        viewBinding.roomAvailableBeds,
                        getString(R.string.room_available_beds_transition)
                    )
                )
                startActivity(viewRoomDetailsIntent, options.toBundle())
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupObserver()
        fetchData()
    }

    private fun fetchData() {
        val roomType = intent.getSerializableExtra(ROOM_TYPE) as RoomType
        viewRoomsViewModel.getRooms(roomType.id)
    }

    private fun setupObserver() {
        viewRoomsViewModel.roomsLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading
                }
                is Resource.Success -> {
                    val rooms = resource.data
                    roomAdapter.submitList(rooms)
                }
                is Resource.Error -> {
                    // Error
                }
            }
        }
    }

    private fun setupView() {
        binding = ActivityViewRoomsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listRoom.apply {
            layoutManager = LinearLayoutManager(this@ViewRoomsActivity)
            adapter = roomAdapter
        }
    }
}