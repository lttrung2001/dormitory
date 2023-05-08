package com.lttrung.dormitory.ui.registerroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.lttrung.dormitory.databinding.ActivityRegisterRoomBinding
import com.lttrung.dormitory.ui.adapters.RoomPagerAdapter
import com.lttrung.dormitory.utils.AppConstants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterRoomActivity : AppCompatActivity() {
    private val binding: ActivityRegisterRoomBinding by lazy {
        ActivityRegisterRoomBinding.inflate(layoutInflater)
    }
    private val roomPagerAdapter: RoomPagerAdapter by lazy {
        RoomPagerAdapter(this@RegisterRoomActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupTabLayoutWithViewPager2()
    }

    private fun setupTabLayoutWithViewPager2() {
        binding.viewPager.adapter = roomPagerAdapter
        val titles = listOf("Details", "Comments")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}