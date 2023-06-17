package com.lttrung.dormitory.ui.activities.registerroom

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.detachTextChangeAnimator
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.R
import com.lttrung.dormitory.databinding.FragmentViewRoomDetailsBinding
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.data.network.models.RoomType
import com.lttrung.dormitory.ui.activities.main.MainActivity
import com.lttrung.dormitory.ui.activities.main.home.HomeViewModel
import com.lttrung.dormitory.ui.dialogs.builder.DialogBuilder
import com.lttrung.dormitory.utils.AppConstants
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewRoomDetailsFragment : Fragment() {
    private val binding: FragmentViewRoomDetailsBinding by lazy {
        FragmentViewRoomDetailsBinding.inflate(layoutInflater)
    }
    private val viewModel: RegisterRoomViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupView()
        setupListener()
        setupObserver()
        return binding.root
    }

    private fun setupObserver() {
        viewModel.registerRoomLiveData.observe(viewLifecycleOwner) { resource ->
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
                    Snackbar.make(
                        requireContext(),
                        binding.root,
                        resource.message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        homeViewModel.roomContractLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    val response = resource.data
                    binding.buttonRegisterRoom.isClickable = true
                    binding.buttonRegisterRoom.hideProgress(R.string.register)
                    val backToMainIntent = Intent(requireContext(), MainActivity::class.java)
                    // 2 flags này có tác dụng: trở về MainActivity trước đó và clear các activity khác
                    // Nếu MainActivity không có trong backstack thì khởi tạo
                    backToMainIntent.addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
                    )
                    backToMainIntent.putExtra(AppConstants.ROOM_CONTRACT_RESPONSE, response)
                    startActivity(backToMainIntent)
                }

                is Resource.Error -> {
                    binding.buttonRegisterRoom.isClickable = true
                    binding.buttonRegisterRoom.hideProgress(R.string.register)
                    Snackbar.make(
                        requireContext(),
                        binding.root,
                        resource.message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupView() {
        val roomType =
            requireActivity().intent.getSerializableExtra(AppConstants.ROOM_TYPE) as RoomType
        val room = requireActivity().intent.getSerializableExtra(AppConstants.ROOM) as Room
        binding.let {
            it.roomImage.load(room.image) {
                crossfade(true)
                placeholder(R.drawable.demo)
            }
            it.roomName.text = room.id.toString()
            it.roomBeds.text = "${roomType.numOfBeds} beds"
            it.roomPrice.text = "${room.price} đ/month"
            it.roomAvailableBeds.text = "${room.availableBeds} available beds"
            it.roomType.text = roomType.name
            it.roomDescription.text = room.description
        }

        bindProgressButton(binding.buttonRegisterRoom)
        binding.buttonRegisterRoom.detachTextChangeAnimator()
    }

    private fun setupListener() {
        binding.buttonRegisterRoom.setOnClickListener {
            // Register
            val dialog = DialogBuilder.Builder(requireContext()).setNoticeTitle(R.string.register_room_entry)
                .setNotice(R.string.ask_for_register_room).addButtonLeft(R.string.agree) {
                    val room =
                        requireActivity().intent.getSerializableExtra(AppConstants.ROOM) as Room
                    viewModel.registerRoom(room.id)
                }.addButtonRight(R.string.cancel).build()
            dialog.show()
        }
    }
}