package com.lttrung.dormitory.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lttrung.dormitory.domain.data.network.models.OutstandingRoom
import com.lttrung.dormitory.databinding.LayoutOutstandingRoomBinding

class OutstandingRoomAdapter :
    ListAdapter<OutstandingRoom, OutstandingRoomAdapter.OutstandingRoomViewHolder>(
        ITEM_CALLBACK
    ) {
    class OutstandingRoomViewHolder(private val binding: LayoutOutstandingRoomBinding) :
        ViewHolder(binding.root) {
        fun bind(outstandingRoom: OutstandingRoom) {
            binding.outstandingRoomName.text = outstandingRoom.name
            binding.oustandingRoomType.text = outstandingRoom.roomType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutstandingRoomViewHolder {
        val binding =
            LayoutOutstandingRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OutstandingRoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OutstandingRoomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<OutstandingRoom>() {
            override fun areItemsTheSame(
                oldItem: OutstandingRoom,
                newItem: OutstandingRoom
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: OutstandingRoom,
                newItem: OutstandingRoom
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}