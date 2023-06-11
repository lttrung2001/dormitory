package com.lttrung.dormitory.ui.activities.main.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.lttrung.dormitory.R
import com.lttrung.dormitory.databinding.FragmentHomeBinding
import com.lttrung.dormitory.databinding.LayoutBillTabTitleBinding
import com.lttrung.dormitory.domain.data.network.models.FetchRoomContractResponse
import com.lttrung.dormitory.ui.adapters.BillPagerAdapter
import com.lttrung.dormitory.ui.adapters.RoomTypeAdapter
import com.lttrung.dormitory.ui.activities.login.LoginActivity
import com.lttrung.dormitory.ui.activities.viewroomtypedetails.ViewRoomTypeDetailsActivity
import com.lttrung.dormitory.utils.AppConstants.*
import com.lttrung.dormitory.utils.ExtensionFunctionHelper.format
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val homeViewModel: HomeViewModel by viewModels()
    private val roomTypeAdapter: RoomTypeAdapter by lazy {
        RoomTypeAdapter { viewBinding, roomType ->
            // Start room type details activity
            val viewRoomTypeDetailsIntent =
                Intent(requireContext(), ViewRoomTypeDetailsActivity::class.java)
            viewRoomTypeDetailsIntent.putExtra(ROOM_TYPE, roomType)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(), androidx.core.util.Pair(
                    viewBinding.roomTypeImage, getString(R.string.room_type_image_transition)
                )
            )
            startActivity(viewRoomTypeDetailsIntent, options.toBundle())
        }
    }
    private val billPagerAdapter: BillPagerAdapter by lazy {
        BillPagerAdapter(this@HomeFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.viewProfile()
        homeViewModel.getRoomTypes()
        homeViewModel.getRoomContract()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Setup view
        setupView()
        // Setup listener
        setupListener()
        // Setup observer
        setupObserver()

        val roomContractResponse =
            requireActivity().intent.getSerializableExtra(ROOM_CONTRACT_RESPONSE) as FetchRoomContractResponse?
        roomContractResponse?.let {
            bindRoomContractData(it)
        }

        return binding.root
    }

    private fun setupView() {
        binding.listRoomType.let {
            it.adapter = roomTypeAdapter
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        // Setup tab layout with view pager 2
        setupTabLayoutWithViewPager2()
    }

    private fun setupObserver() {
        homeViewModel.viewProfileLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val studentProfile = resource.data
                    binding.fullName.text = studentProfile.fullName
                }
                is Resource.Error -> {
                    startActivity(
                        Intent(requireActivity(), LoginActivity::class.java).addFlags(
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        )
                    )
                }
            }
        }

        homeViewModel.roomTypesLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.errorRoomTypes.text = ""
                }
                is Resource.Success -> {
                    binding.errorRoomTypes.text = ""
                    val roomTypes = resource.data
                    roomTypeAdapter.submitList(roomTypes)
                }
                is Resource.Error -> {
                    binding.errorRoomTypes.text = resource.message
                }
            }
        }

        homeViewModel.roomContractLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.errorCurrentRoom.text = ""
                }
                is Resource.Success -> {
                    binding.errorCurrentRoom.text = ""

                    binding.titleCurrentRoom.visibility = View.VISIBLE
                    binding.currentRoomContainer.visibility = View.VISIBLE

                    val response = resource.data
                    // Bind room contract
                    bindRoomContractData(response)
                }
                is Resource.Error -> {
                    binding.titleCurrentRoom.visibility = View.GONE
                    binding.currentRoomContainer.visibility = View.GONE
                }
            }
        }

        homeViewModel.cancelContractLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.errorCurrentRoom.text = ""
                }
                is Resource.Success -> {
                    homeViewModel.getRoomContract()
                }
                is Resource.Error -> {
                    binding.errorCurrentRoom.text = resource.message
                }
            }
        }

        homeViewModel.extendRoomLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.errorCurrentRoom.text = ""
                }
                is Resource.Success -> {
                    homeViewModel.getRoomContract()
                }
                is Resource.Error -> {
                    binding.errorCurrentRoom.text = resource.message
                }
            }
        }
    }

    private fun bindRoomContractData(response: FetchRoomContractResponse) {
        binding.titleCurrentRoom.visibility = View.VISIBLE
        binding.currentRoomContainer.visibility = View.VISIBLE

        val contract = response.contract
        binding.let {
            it.roomId.text = contract.roomId.toString()
            it.roomType.text = response.roomTypeName
            it.roomBeds.text = "0"
            it.startDate.text = response.dateFrom.format()
            it.endDate.text = response.dateEnd.format()
            if (contract.status) {
                if (response.dateEnd.before(Date())) {
                    binding.buttonPay.visibility = View.VISIBLE
                    binding.buttonPay.text = getString(R.string.expand)
                    // Can extend
                    binding.buttonPay.setOnClickListener {
                        // Extend
                        homeViewModel.extendRoom()
                    }
                } else {
                    binding.buttonPay.visibility = View.GONE
                    binding.buttonCancel.visibility = View.GONE
                }
            } else {
                binding.buttonPay.visibility = View.VISIBLE
                binding.buttonCancel.visibility = View.VISIBLE
                // Can pay or cancel
                binding.buttonPay.setOnClickListener {
                    // Pay
                }
                binding.buttonCancel.setOnClickListener {
                    // Cancel
                    AlertDialog.Builder(context).setTitle("Cancel contract entry")
                        .setMessage("Are you sure to cancel contract?")
                        .setPositiveButton(android.R.string.yes) { dialog, which ->
                            homeViewModel.cancelContract()
                        }
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(R.drawable.ic_cancel_presentation_24).show()
                }
            }
        }
    }

    private fun setupListener() {

    }

    private fun setupTabLayoutWithViewPager2() {
        binding.viewPagerBill.adapter = billPagerAdapter
        TabLayoutMediator(binding.tabLayoutBill, binding.viewPagerBill) { tab, position ->
            tab.text = BILL_TAB_TITLES[position]
        }.attach()

        for (i in BILL_TAB_TITLES.indices) {
            val titleTextView = LayoutBillTabTitleBinding.inflate(LayoutInflater.from(context)).root
            titleTextView.text = BILL_TAB_TITLES[i]
            binding.tabLayoutBill.getTabAt(i)?.let {
                it.customView = titleTextView
            }
        }
    }
}