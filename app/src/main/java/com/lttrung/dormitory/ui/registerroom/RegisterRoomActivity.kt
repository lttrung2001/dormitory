package com.lttrung.dormitory.ui.registerroom

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.detachTextChangeAnimator
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.R
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.data.network.models.RoomType
import com.lttrung.dormitory.databinding.ActivityRegisterRoomBinding
import com.lttrung.dormitory.ui.main.MainActivity
import com.lttrung.dormitory.ui.main.home.HomeViewModel
import com.lttrung.dormitory.utils.AppConstants.*
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterRoomActivity : AppCompatActivity() {
    private val binding: ActivityRegisterRoomBinding by lazy {
        ActivityRegisterRoomBinding.inflate(layoutInflater)
    }
    private val viewModel: RegisterRoomViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
        setupListener()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.registerRoomLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Loading
                    binding.buttonRegisterRoom.isClickable = false
                    binding.buttonRegisterRoom.showProgress {
                        buttonTextRes = R.string.loading
                        progressColor = Color.WHITE
                    }
                }
                is Resource.Success -> {
                    // Register successfully
                    homeViewModel.getRoomContract()
                }
                is Resource.Error -> {
                    binding.buttonRegisterRoom.isClickable = true
                    binding.buttonRegisterRoom.hideProgress(R.string.register)
                    Snackbar.make(this, binding.root, resource.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }

        homeViewModel.roomContractLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val response = resource.data
                    binding.buttonRegisterRoom.isClickable = true
                    binding.buttonRegisterRoom.hideProgress(R.string.register)
                    val backToMainIntent = Intent(this, MainActivity::class.java)
                    // 2 flags này có tác dụng: trở về MainActivity trước đó và clear các activity khác
                    // Nếu MainActivity không có trong backstack thì khởi tạo
                    backToMainIntent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
                    )
                    backToMainIntent.putExtra(ROOM_CONTRACT_RESPONSE, response)
                    startActivity(backToMainIntent)
                }
                is Resource.Error -> {
                    binding.buttonRegisterRoom.isClickable = true
                    binding.buttonRegisterRoom.hideProgress(R.string.register)
                    Snackbar.make(this, binding.root, resource.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupView() {
        val roomType = intent.getSerializableExtra(ROOM_TYPE) as RoomType
        val room = intent.getSerializableExtra(ROOM) as Room
        binding.let {
            it.roomImage.load(room.image) {
                crossfade(true)
                placeholder(R.drawable.demo)
            }
            it.roomName.text = room.name
            it.roomBeds.text = "${roomType.numOfBeds} beds"
            it.roomPrice.text = "${room.price} đ/month"
            it.roomAvailableBeds.text = "${room.availableBeds} available beds"
            it.roomDescription.text = room.description
        }

        bindProgressButton(binding.buttonRegisterRoom)
        binding.buttonRegisterRoom.detachTextChangeAnimator()
    }

    private fun setupListener() {
        binding.buttonRegisterRoom.setOnClickListener {
            // Register
            val room = intent.getSerializableExtra(ROOM) as Room
            viewModel.registerRoom(room.id)
        }
    }
}