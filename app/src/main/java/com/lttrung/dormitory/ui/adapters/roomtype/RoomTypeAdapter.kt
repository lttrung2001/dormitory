package com.lttrung.dormitory.ui.adapters.roomtype

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lttrung.dormitory.database.data.network.models.RoomType
import com.lttrung.dormitory.databinding.LayoutRoomTypeBinding


class RoomTypeAdapter : ListAdapter<RoomType, RoomTypeAdapter.RoomTypeViewHolder>(ITEM_CALLBACK) {
    class RoomTypeViewHolder(private val binding: LayoutRoomTypeBinding) : ViewHolder(binding.root) {
        fun bind(roomType: RoomType) {
            binding.roomTypeName.text = roomType.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomTypeViewHolder {
        val binding =
            LayoutRoomTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomTypeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<RoomType>() {
            override fun areItemsTheSame(oldItem: RoomType, newItem: RoomType): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RoomType, newItem: RoomType): Boolean {
                return oldItem == newItem
            }
        }
    }
}