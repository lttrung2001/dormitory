package com.lttrung.dormitory.ui.registerroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lttrung.dormitory.databinding.FragmentViewRoomCommentsBinding
import com.lttrung.dormitory.domain.data.network.models.Comment
import com.lttrung.dormitory.ui.adapters.CommentAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.random.Random

@AndroidEntryPoint
class ViewRoomCommentsFragment : Fragment() {
    private val binding: FragmentViewRoomCommentsBinding by lazy {
        FragmentViewRoomCommentsBinding.inflate(layoutInflater)
    }
    private val commentAdapter: CommentAdapter by lazy {
        val data = mutableListOf<Comment>()
        for (i in 1..20) {
            val randomInt = Random.nextInt()
            data.add(
                if (randomInt % 2 == 0) {
                    Comment(
                        randomInt,
                        "n19dccn214",
                        1,
                        "Em từ React native đá quá Flutter được mấy hôm, mọi người cho em hỏi là ở công mọi người thường dùng thư viện gì để tạo navigation vậy.",
                        Date()
                    )
                } else {
                    Comment(
                        randomInt,
                        "Người khác",
                        1,
                        "Sài Gòn vào mùa mưa rồi, bắt đầu bằng một cơn mưa như bão",
                        Date()
                    )
                }
            )
        }
        val adapter = CommentAdapter("n19dccn214") { comment ->
            val currentList = commentAdapter.currentList.toMutableList()
            currentList.remove(comment)
            commentAdapter.submitList(currentList)
        }
        adapter.submitList(data)
        adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.comments.layoutManager = LinearLayoutManager(requireContext())
        binding.comments.adapter = commentAdapter
        return binding.root
    }
}