package com.lttrung.dormitory.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lttrung.dormitory.databinding.LayoutBillBinding
import com.lttrung.dormitory.domain.data.network.models.WaterBill
import java.util.*

class WaterBillAdapter : ListAdapter<WaterBill, WaterBillAdapter.WaterBillViewHolder>(ITEM_CALLBACK) {
    class WaterBillViewHolder(private val binding: LayoutBillBinding) : ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(waterBill: WaterBill) {
            val waterCostByMonth = waterBill.waterCostByMonth

            binding.time.text = "Undefined"
            binding.cost.text = "Undefined"

            waterCostByMonth?.let {
                binding.time.text = "${waterCostByMonth.month} ${waterCostByMonth.year}"
                binding.cost.text = "${waterCostByMonth.cost}đ / m3"
            }

            binding.roomName.text = waterBill.roomId.toString()
            binding.usage.text = "${waterBill.waterUsage} m3"
            binding.totalCost.text = "${waterBill.totalCost}đ"
            binding.status.setTextColor(
                if (waterBill.status) {
                    binding.status.text = "Paid"
                    Color.GREEN
                } else {
                    binding.status.text = "Unpaid"
                Color.RED
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterBillViewHolder {
        val binding = LayoutBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WaterBillViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WaterBillViewHolder, position: Int) {
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