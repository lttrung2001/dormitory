package com.lttrung.dormitory.ui.activities.viewrooms

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import com.lttrung.dormitory.R
import com.lttrung.dormitory.databinding.ActivityViewRoomsBinding
import com.lttrung.dormitory.domain.data.local.models.FilterSortModel
import com.lttrung.dormitory.domain.data.network.models.RoomType
import com.lttrung.dormitory.ui.adapters.RoomAdapter
import com.lttrung.dormitory.ui.activities.registerroom.RegisterRoomActivity
import com.lttrung.dormitory.utils.AppConstants.ROOM
import com.lttrung.dormitory.utils.AppConstants.ROOM_TYPE
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ViewRoomsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewRoomsBinding
    private val viewRoomsViewModel: ViewRoomsViewModel by viewModels()
    private val roomAdapter: RoomAdapter by lazy {
        RoomAdapter { viewBinding, room ->
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupObserver()
        setupListener()
        viewRoomsViewModel.getRooms(getRoomTypeId(), null)
    }

    private fun setupListener() {
        binding.buttonOpenFilter.setOnClickListener {
            var maxBed = 1
            var minBed = 0
            val values = mutableListOf(minBed, maxBed)
            var sortPosition = 0
            if (viewRoomsViewModel.filterSortLiveData.value != null) {
                val value = viewRoomsViewModel.filterSortLiveData.value!!
                maxBed = value.maxBeds
                minBed = value.minBeds
                values[0] = value.bedsRange[0]
                values[1] = value.bedsRange[1]
                sortPosition = value.sortPosition
            } else if (roomAdapter.currentList.isNotEmpty()) {
                maxBed = roomAdapter.currentList.maxOf { it.availableBeds }
                minBed = roomAdapter.currentList.minOf { it.availableBeds }
                minBed = if (minBed == maxBed) 0 else minBed
                values[0] = minBed
                values[1] = maxBed
            }
            val fragment = FilterSortFragment(
                FilterSortModel(
                    minBed,
                    maxBed,
                    values,
                    sortPosition
                )
            )
            fragment.show(supportFragmentManager, FilterSortFragment.TAG)
        }
    }

    private fun getRoomTypeId(): Int {
        val roomType = intent.getSerializableExtra(ROOM_TYPE) as RoomType
        return roomType.id
    }

    private fun setupObserver() {
        viewRoomsViewModel.filterSortLiveData.observe(this) { filterSort ->
            viewRoomsViewModel.getRooms(getRoomTypeId(), filterSort)
        }

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
                    binding.errorText.text = "Can not view rooms at this time!"
                    binding.buttonGoBack.setOnClickListener {
                        finish()
                    }
                    binding.errorText.visibility = View.VISIBLE
                    binding.buttonGoBack.visibility = View.VISIBLE
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