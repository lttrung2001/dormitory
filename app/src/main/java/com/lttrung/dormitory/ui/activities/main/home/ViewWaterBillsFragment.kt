package com.lttrung.dormitory.ui.activities.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.databinding.FragmentWaterBillsBinding
import com.lttrung.dormitory.ui.adapters.WaterBillAdapter
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewWaterBillsFragment : Fragment() {
    private val binding: FragmentWaterBillsBinding by lazy {
        FragmentWaterBillsBinding.inflate(layoutInflater)
    }
    private val viewWaterBillsViewModel: ViewWaterBillsViewModel by viewModels()
    private val waterBillAdapter: WaterBillAdapter by lazy {
        val adapter = WaterBillAdapter()
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewWaterBillsViewModel.getWaterBills()
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
        return binding.root
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
                        binding.root,
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
        binding.listBill.let {
            it.adapter = waterBillAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }
}