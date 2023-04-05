package com.lttrung.dormitory.ui.main.home.viewbills

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lttrung.dormitory.database.data.network.models.WaterBill
import com.lttrung.dormitory.database.data.network.models.WaterCostByMonth
import com.lttrung.dormitory.databinding.FragmentUnpaidBillBinding
import com.lttrung.dormitory.ui.adapters.WaterBillAdapter
import kotlin.random.Random

class ViewElectricBillFragment : Fragment() {
    private var binding: FragmentUnpaidBillBinding? = null
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
        binding = FragmentUnpaidBillBinding.inflate(layoutInflater)
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
        binding!!.listBill.let {
            it.adapter = waterBillAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }
}