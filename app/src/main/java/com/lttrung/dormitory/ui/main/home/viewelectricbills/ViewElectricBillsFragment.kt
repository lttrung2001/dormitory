package com.lttrung.dormitory.ui.main.home.viewelectricbills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.database.data.network.models.ElectricBill
import com.lttrung.dormitory.database.data.network.models.ElectricCostByMonth
import com.lttrung.dormitory.databinding.FragmentElectricBillsBinding
import com.lttrung.dormitory.ui.adapters.ElectricBillAdapter
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class ViewElectricBillsFragment : Fragment() {
    private var binding: FragmentElectricBillsBinding? = null
    private val viewElectricBillsViewModel: ViewElectricBillsViewModel by viewModels()
    private val electricBillAdapter: ElectricBillAdapter by lazy {
        val adapter = ElectricBillAdapter()
        val data = mutableListOf<ElectricBill>()
        for (i in 1..10) {
            data.add(
                ElectricBill(
                    Random.nextInt(1, 1000),
                    Random.nextInt(1, 1000),
                    Random.nextInt(1, 1000),
                    Random.nextInt(1, 1000) % 2 == 0,
                    ElectricCostByMonth(
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
        binding = FragmentElectricBillsBinding.inflate(layoutInflater)

        viewElectricBillsViewModel.getElectricBills()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup view
        setupView()
        // Setup listener
        setupListener()
        // Setup observer
        setupObserver()
    }

    private fun setupObserver() {
        viewElectricBillsViewModel.electricBillsLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val electricBills = resource.data
                    electricBillAdapter.submitList(electricBills)
                }
                is Resource.Error -> {
//                    Snackbar.make(
//                        requireContext(),
//                        binding!!.root,
//                        resource.message,
//                        Snackbar.LENGTH_LONG
//                    ).show()
                }
            }
        }
    }

    private fun setupListener() {

    }

    private fun setupView() {
        binding!!.listBill.let {
            it.adapter = electricBillAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }
}