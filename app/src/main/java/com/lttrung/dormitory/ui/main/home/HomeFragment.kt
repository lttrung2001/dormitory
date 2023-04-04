package com.lttrung.dormitory.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lttrung.dormitory.database.data.network.models.OutstandingRoom
import com.lttrung.dormitory.database.data.network.models.RoomType
import com.lttrung.dormitory.databinding.FragmentHomeBinding
import com.lttrung.dormitory.ui.adapters.roomtype.OutstandingRoomAdapter
import com.lttrung.dormitory.ui.adapters.roomtype.RoomTypeAdapter

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val roomTypeAdapter: RoomTypeAdapter by lazy {
        val adapter = RoomTypeAdapter()
        val data = mutableListOf<RoomType>()
        data.add(RoomType(1, "Basic",""))
        data.add(RoomType(2, "Medium", ""))
        data.add(RoomType(3, "High End", ""))
        adapter.submitList(data)
        adapter
    }

    private val outstandingRoomAdapter: OutstandingRoomAdapter by lazy {
        val adapter = OutstandingRoomAdapter()
        val data = mutableListOf<OutstandingRoom>()
        data.add(OutstandingRoom(1, "Room name 1","Basic", ""))
        data.add(OutstandingRoom(2, "Room name 2", "High End", ""))
        data.add(OutstandingRoom(3, "Room name 3", "Medium", ""))
        adapter.submitList(data)
        adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.listRoomType.let {
            it.adapter = roomTypeAdapter
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding!!.listOutstandingRoom.let {
            it.adapter = outstandingRoomAdapter
            it.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}