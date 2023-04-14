package com.lttrung.dormitory.ui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.lttrung.dormitory.R
import com.lttrung.dormitory.database.data.network.models.RoomType
import com.lttrung.dormitory.databinding.LayoutRoomTypeBinding
import com.lttrung.dormitory.ui.adapters.listeners.RoomTypeListener


class RoomTypeAdapter(
    private val listener: RoomTypeListener
) : ListAdapter<RoomType, RoomTypeAdapter.RoomTypeViewHolder>(ITEM_CALLBACK) {
    class RoomTypeViewHolder(private val binding: LayoutRoomTypeBinding) : ViewHolder(binding.root) {
        fun bind(roomType: RoomType, listener: RoomTypeListener) {
            binding.roomTypeImage.load(roomType.image) {
                crossfade(true)
                placeholder(R.drawable.demo)
            }
            binding.roomTypeName.text = roomType.name
            binding.root.setOnClickListener { listener.onClick(roomType) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomTypeViewHolder {
        val binding =
            LayoutRoomTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomTypeViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
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