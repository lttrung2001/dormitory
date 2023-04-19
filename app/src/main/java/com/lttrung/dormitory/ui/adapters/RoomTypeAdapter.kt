package com.lttrung.dormitory.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.lttrung.dormitory.R
import com.lttrung.dormitory.databinding.LayoutRoomTypeBinding
import com.lttrung.dormitory.domain.data.network.models.RoomType


class RoomTypeAdapter(
    private val onClick: (viewBinding: LayoutRoomTypeBinding, roomType: RoomType) -> Unit
) : ListAdapter<RoomType, RoomTypeAdapter.RoomTypeViewHolder>(ITEM_CALLBACK) {
    class RoomTypeViewHolder(private val binding: LayoutRoomTypeBinding) : ViewHolder(binding.root) {
        fun bind(
            roomType: RoomType,
            onClick: (viewBinding: LayoutRoomTypeBinding, roomType: RoomType) -> Unit
        ) {
            binding.roomTypeImage.load(roomType.image) {
                crossfade(true)
                placeholder(R.drawable.demo)
            }
            binding.roomTypeName.text = roomType.name
            binding.root.setOnClickListener {
                onClick(binding, roomType)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomTypeViewHolder {
        val binding =
            LayoutRoomTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomTypeViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
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