package com.lttrung.dormitory.ui.registerroom

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.lttrung.dormitory.databinding.ActivityRegisterRoomBinding
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.ui.adapters.RoomPagerAdapter
import com.lttrung.dormitory.utils.AppConstants.ROOM
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterRoomActivity : AppCompatActivity() {
    private val binding: ActivityRegisterRoomBinding by lazy {
        ActivityRegisterRoomBinding.inflate(layoutInflater)
    }
    private val roomPagerAdapter: RoomPagerAdapter by lazy {
        RoomPagerAdapter(this@RegisterRoomActivity)
    }
    private val registerRoomViewModel: RegisterRoomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupData()

        setupTabLayoutWithViewPager2()
    }

    private fun setupData() {
        val room = intent.getSerializableExtra(ROOM) as Room
        registerRoomViewModel.roomId = room.id
    }

    private fun setupTabLayoutWithViewPager2() {
        binding.viewPager.adapter = roomPagerAdapter
        val titles = listOf("Details", "Comments")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}