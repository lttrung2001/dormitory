package com.lttrung.dormitory.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lttrung.dormitory.database.data.network.models.WaterBill
import com.lttrung.dormitory.databinding.LayoutBillBinding
import java.util.*

class ElectricBillAdapter : ListAdapter<WaterBill, ElectricBillAdapter.ElectricBillViewHolder>(ITEM_CALLBACK) {
    class ElectricBillViewHolder(private val binding: LayoutBillBinding) : ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(waterBill: WaterBill) {
            val waterCostByMonth = waterBill.waterCostByMonth
            val calendar = Calendar.getInstance().also {
                it.set(waterCostByMonth.year, waterCostByMonth.month, 1)
                return@also
            }
            binding.roomName.text = waterBill.roomId.toString()
            binding.time.text = "${calendar.get(Calendar.MONTH)} ${calendar.get(Calendar.YEAR)}"
            binding.usage.text = "${waterBill.waterUsage} kWh"
            binding.cost.text = "${waterCostByMonth.cost}đ / kWh"
            binding.totalCost.text = "${waterBill.totalCost}đ"
            binding.status.setTextColor(if (waterBill.status) {
                binding.status.text = "Paid"
                Color.GREEN
            } else {
                binding.status.text = "Unpaid"
                Color.RED
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectricBillViewHolder {
        val binding = LayoutBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ElectricBillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ElectricBillViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val ITEM_CALLBACK = object : ItemCallback<WaterBill>() {
            override fun areItemsTheSame(oldItem: WaterBill, newItem: WaterBill): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: WaterBill, newItem: WaterBill): Boolean {
                return oldItem == newItem
            }
        }
    }
}