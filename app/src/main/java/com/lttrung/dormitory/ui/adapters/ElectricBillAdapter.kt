package com.lttrung.dormitory.ui.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lttrung.dormitory.database.data.network.models.ElectricBill
import com.lttrung.dormitory.databinding.LayoutBillBinding
import java.util.*

class ElectricBillAdapter :
    ListAdapter<ElectricBill, ElectricBillAdapter.ElectricBillViewHolder>(ITEM_CALLBACK) {
    class ElectricBillViewHolder(private val binding: LayoutBillBinding) :
        ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(electricBill: ElectricBill) {
            val electricCostByMonth = electricBill.electricCostByMonth
            binding.roomName.text = electricBill.roomId.toString()
            binding.time.text = "${electricCostByMonth.month} ${electricCostByMonth.year} "
            binding.usage.text = "${electricBill.waterUsage} kWh"
            binding.cost.text = "${electricCostByMonth.cost}đ / kWh"
            binding.totalCost.text = "${electricBill.totalCost}đ"
            binding.status.setTextColor(
                if (electricBill.status) {
                    binding.status.text = "Paid"
                    Color.GREEN
                } else {
                    binding.status.text = "Unpaid"
                    Color.RED
                }
            )
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
        private val ITEM_CALLBACK = object : ItemCallback<ElectricBill>() {
            override fun areItemsTheSame(oldItem: ElectricBill, newItem: ElectricBill): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ElectricBill, newItem: ElectricBill): Boolean {
                return oldItem == newItem
            }
        }
    }
}