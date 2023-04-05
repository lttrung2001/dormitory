package com.lttrung.dormitory.ui.main.home.viewwaterbills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.database.data.network.models.WaterBill
import com.lttrung.dormitory.database.data.network.models.WaterCostByMonth
import com.lttrung.dormitory.databinding.FragmentPaidBillBinding
import com.lttrung.dormitory.ui.adapters.WaterBillAdapter
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random
@AndroidEntryPoint
class ViewWaterBillsFragment : Fragment() {
    private var binding: FragmentPaidBillBinding? = null
    private val viewWaterBillsViewModel: ViewWaterBillsViewModel by viewModels()
    private val waterBillAdapter: WaterBillAdapter by lazy {
        val adapter = WaterBillAdapter()
        val data = mutableListOf<WaterBill>()
        for (i in 1..10) {
            data.add(
                WaterBill(
                    Random.nextInt(1, 1000),
                    Random.nextInt(1, 1000),
                    Random.nextInt(1, 1000),
                    Random.nextInt(1, 1000) % 2 == 0,
                    WaterCostByMonth(
                        Random.nextInt(1, 1000),
                        Random.nextInt(1, 12),
                        2023,
                        Random.nextInt(1, 1000).toDouble()
                    ),
                    Random.nextInt(1, 1000).toDouble()
                )
            )
        }
        adapter.submitList(data)
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPaidBillBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Setup view
        setupView()
        // Setup listener
        setupListener()
        // Setup observer
        setupObserver()
        return binding!!.root
    }

    private fun setupObserver() {
        viewWaterBillsViewModel.waterBillsLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val waterBills = resource.data
                    waterBillAdapter.submitList(waterBills)
                }
                is Resource.Error -> {
                    Snackbar.make(
                        requireContext(),
                        binding!!.root,
                        resource.message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setupListener() {

    }

    private fun setupView() {
        binding!!.listBill.let {
            it.adapter = waterBillAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }
}