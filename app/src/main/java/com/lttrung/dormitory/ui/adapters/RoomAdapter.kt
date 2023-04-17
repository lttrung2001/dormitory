package com.lttrung.dormitory.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.lttrung.dormitory.R
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.databinding.LayoutRoomBinding

class RoomAdapter(private val itemListener: ItemListener) :
    ListAdapter<Room, RoomAdapter.RoomViewHolder>(ITEM_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val binding = LayoutRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(getItem(position), itemListener)
    }

    companion object {
        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<Room>() {
            override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
                return oldItem == newItem
            }
        }
    }


    interface ItemListener {
        fun onClick(room: Room)
    }

    class RoomViewHolder(private val binding: LayoutRoomBinding) : ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(room: Room, listener: ItemListener) {
            // Coil
            binding.roomImage.load(room.image) {
                crossfade(true)
                placeholder(R.drawable.demo)
            }
            binding.roomId.text = room.id.toString()
            binding.roomPrice.text = "${room.price} vnđ"
            binding.roomAvailableBeds.text = "${room.availableBeds} available beds"
            binding.root.setOnClickListener {
                listener.onClick(room)
            }
        }
    }
}