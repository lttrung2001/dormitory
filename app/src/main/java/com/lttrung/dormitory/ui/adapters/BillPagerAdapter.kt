package com.lttrung.dormitory.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lttrung.dormitory.ui.main.dashboard.DashboardFragment
import com.lttrung.dormitory.ui.main.home.viewbills.ViewWaterBillFragment
import com.lttrung.dormitory.ui.main.home.viewbills.ViewElectricBillFragment

class BillPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ViewElectricBillFragment()
            1 -> ViewWaterBillFragment()
            else -> DashboardFragment()
        }
    }
}