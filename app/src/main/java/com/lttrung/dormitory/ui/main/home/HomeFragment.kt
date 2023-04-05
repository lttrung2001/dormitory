package com.lttrung.dormitory.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.lttrung.dormitory.database.data.network.models.OutstandingRoom
import com.lttrung.dormitory.database.data.network.models.RoomType
import com.lttrung.dormitory.databinding.FragmentHomeBinding
import com.lttrung.dormitory.databinding.LayoutBillTabTitleBinding
import com.lttrung.dormitory.ui.adapters.BillPagerAdapter
import com.lttrung.dormitory.ui.adapters.OutstandingRoomAdapter
import com.lttrung.dormitory.ui.adapters.RoomTypeAdapter
import com.lttrung.dormitory.ui.adapters.listeners.RoomTypeListener
import com.lttrung.dormitory.ui.viewroomtypedetails.ViewRoomTypeDetailsActivity
import com.lttrung.dormitory.utils.AppConstants.BILL_TAB_TITLES
import com.lttrung.dormitory.utils.AppConstants.ROOM_TYPE
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()
    private var roomTypeAdapter: RoomTypeAdapter? = null
    private var outstandingRoomAdapter: OutstandingRoomAdapter? = null
    private var billPagerAdapter: BillPagerAdapter? = null

    private fun createRoomTypeAdapter(): RoomTypeAdapter {
        val listener = object : RoomTypeListener {
            override fun onClick(roomType: RoomType) {
                // Start room type details activity
                val viewRoomTypeDetailsIntent = Intent(requireContext(), ViewRoomTypeDetailsActivity::class.java)
                viewRoomTypeDetailsIntent.putExtra(ROOM_TYPE, roomType)
                startActivity(viewRoomTypeDetailsIntent)
            }
        }
        val adapter = RoomTypeAdapter(listener)
        val data = mutableListOf<RoomType>()
        data.add(RoomType(1, "Basic", 1, 400000.0, "No description", ""))
        data.add(RoomType(1, "Basic", 1, 400000.0, "No description", ""))
        data.add(RoomType(1, "Basic", 1, 400000.0, "No description", ""))
        adapter.submitList(data)
        return adapter
    }

    private fun createOutstandingRoomAdapter(): OutstandingRoomAdapter {
        val adapter = OutstandingRoomAdapter()
        val data = mutableListOf<OutstandingRoom>()
        data.add(OutstandingRoom(1, "Room name 1", "Basic", ""))
        data.add(OutstandingRoom(2, "Room name 2", "High End", ""))
        data.add(OutstandingRoom(3, "Room name 3", "Medium", ""))
        adapter.submitList(data)
        return adapter
    }

    private fun createBillPagerAdapter(): BillPagerAdapter {
        return BillPagerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getRoomTypes()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        // Setup view
        setupView()
        // Setup listener
        setupListener()
        // Setup observer
        setupObserver()

        return binding!!.root
    }

    private fun setupView() {
        binding!!.listRoomType.let {
            it.adapter = roomTypeAdapter ?: createRoomTypeAdapter()
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding!!.listOutstandingRoom.let {
            it.adapter = outstandingRoomAdapter ?: createOutstandingRoomAdapter()
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(binding!!.listOutstandingRoom)
        }

        // Setup tab layout with view pager 2
        setupTabLayoutWithViewPager2()
    }

    private fun setupObserver() {
        homeViewModel.roomTypesLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val roomTypes = resource.data
                    roomTypeAdapter?.submitList(roomTypes)
                }
                is Resource.Error -> {
                    Snackbar.make(
                        requireContext(),
                        binding!!.root,
                        resource.message,
                        Snackbar.LENGTH_LONG
                    )
                        .show()
                }
            }
        }
    }

    private fun setupListener() {

    }

    private fun setupTabLayoutWithViewPager2() {
        binding!!.viewPagerBill.adapter = billPagerAdapter ?: createBillPagerAdapter()
        TabLayoutMediator(binding!!.tabLayoutBill, binding!!.viewPagerBill) { tab, position ->
            tab.text = BILL_TAB_TITLES[position]
        }.attach()

        for (i in BILL_TAB_TITLES.indices) {
            val titleTextView = LayoutBillTabTitleBinding.inflate(LayoutInflater.from(context)).root
            titleTextView.text = BILL_TAB_TITLES[i]
            binding!!.tabLayoutBill.getTabAt(i)?.let {
                it.customView = titleTextView
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}