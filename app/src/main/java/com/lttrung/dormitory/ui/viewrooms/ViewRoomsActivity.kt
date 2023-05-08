package com.lttrung.dormitory.ui.viewrooms

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import com.lttrung.dormitory.R
import com.lttrung.dormitory.databinding.ActivityViewRoomsBinding
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
    private val spinnerAdapter: ArrayAdapter<String> by lazy {
        val sortByList = listOf("Available beds ascending", "Available beds descending")
        val adapter =
            ArrayAdapter(this@ViewRoomsActivity, R.layout.layout_sort_by, sortByList)
        adapter
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
                    setupSpinner()
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

    private fun setupSpinner() {
        binding.spinnerSortBy.adapter = spinnerAdapter
        binding.spinnerSortBy.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        roomAdapter.submitList(roomAdapter.currentList.toMutableList().sortedBy {
                            it.availableBeds
                        })
                    }
                    1 -> {
                        roomAdapter.submitList(
                            roomAdapter.currentList.toMutableList().sortedByDescending {
                                it.availableBeds
                            })
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
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