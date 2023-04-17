package com.lttrung.dormitory.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lttrung.dormitory.ui.main.dashboard.DashboardFragment
import com.lttrung.dormitory.ui.main.home.ViewWaterBillsFragment
import com.lttrung.dormitory.ui.main.home.ViewElectricBillsFragment

class BillPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ViewElectricBillsFragment()
            1 -> ViewWaterBillsFragment()
            else -> DashboardFragment()
        }
    }
}