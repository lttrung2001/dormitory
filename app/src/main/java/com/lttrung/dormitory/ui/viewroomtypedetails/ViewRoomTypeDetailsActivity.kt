package com.lttrung.dormitory.ui.viewroomtypedetails

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lttrung.dormitory.database.data.network.models.RoomType
import com.lttrung.dormitory.databinding.ActivityViewRoomTypeDetailsBinding
import com.lttrung.dormitory.ui.viewrooms.ViewRoomsActivity
import com.lttrung.dormitory.utils.AppConstants.ROOM_TYPE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewRoomTypeDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewRoomTypeDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()
    }

    private fun setupListener() {
        binding.buttonSelectRoom.setOnClickListener {
            val roomType = intent.getSerializableExtra(ROOM_TYPE) as RoomType
            startActivity(
                Intent(
                    this@ViewRoomTypeDetailsActivity,
                    ViewRoomsActivity::class.java
                ).apply {
                    putExtra(ROOM_TYPE, roomType)
                })
        }
    }

    private fun setupView() {
        binding = ActivityViewRoomTypeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindData()
    }

    @SuppressLint("SetTextI18n")
    private fun bindData() {
        val roomType = intent.getSerializableExtra(ROOM_TYPE) as RoomType
        // Replace by coil
//        binding.roomTypeImage.setImageURI(Uri.parse(roomType.image))
        binding.roomTypeName.text = roomType.name
        binding.roomTypeCost.text = "${roomType.cost} vnđ / month"
        binding.roomTypeBeds.text = "${roomType.numOfBeds} beds"
        binding.roomTypeDescription.text = roomType.description
    }
}