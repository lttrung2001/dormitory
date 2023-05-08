package com.lttrung.dormitory.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lttrung.dormitory.ui.main.dashboard.DashboardFragment
import com.lttrung.dormitory.ui.main.home.ViewWaterBillsFragment
import com.lttrung.dormitory.ui.main.home.ViewElectricBillsFragment
import com.lttrung.dormitory.ui.registerroom.ViewRoomCommentsFragment
import com.lttrung.dormitory.ui.registerroom.ViewRoomDetailsFragment

class RoomPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ViewRoomDetailsFragment()
            1 -> ViewRoomCommentsFragment()
            else -> DashboardFragment()
        }
    }
}