package com.lttrung.dormitory.ui.activities.registerroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.databinding.FragmentViewRoomCommentsBinding
import com.lttrung.dormitory.domain.data.network.models.CommentNetworkModel
import com.lttrung.dormitory.ui.adapters.CommentAdapter
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ViewRoomCommentsFragment : Fragment() {
    private val binding: FragmentViewRoomCommentsBinding by lazy {
        FragmentViewRoomCommentsBinding.inflate(layoutInflater)
    }
    private val registerRoomViewModel: RegisterRoomViewModel by activityViewModels()
    private val viewRoomCommentViewModel: ViewRoomCommentsViewModel by viewModels()
    private val commentAdapter: CommentAdapter by lazy {
        val adapter = CommentAdapter { comment ->
            viewRoomCommentViewModel.deleteComment(comment.id)
        }
        adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupRecyclerView()
        setupListener()
        setupObserver()
        viewRoomCommentViewModel.getComments(registerRoomViewModel.roomId)
        return binding.root
    }

    private fun setupListener() {
        binding.buttonComment.setOnClickListener {
            val commentContent = binding.commentContent.text.trim().toString()
            viewRoomCommentViewModel.writeComment(
                CommentNetworkModel(
                    0,
                    "",
                    registerRoomViewModel.roomId,
                    commentContent,
                    Date()
                )
            )
        }
    }

    private fun setupObserver() {
        viewRoomCommentViewModel.writeCommentLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val comment = resource.data
                    val currentList = commentAdapter.currentList.toMutableList()
                    currentList.add(0, comment)
                    commentAdapter.submitList(currentList)

                    binding.commentContent.setText("")
                }
                is Resource.Error -> {
                    Snackbar.make(
                        requireContext(),
                        binding.linearLayout,
                        resource.message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        viewRoomCommentViewModel.deleteCommentLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val deletedCommentId = resource.data
                    val currentList = commentAdapter.currentList.toMutableList()
                    currentList.removeIf {
                        it.id == deletedCommentId
                    }
                    commentAdapter.submitList(currentList)
                }
                is Resource.Error -> {
                    Snackbar.make(
                        requireContext(),
                        binding.linearLayout,
                        resource.message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        viewRoomCommentViewModel.getCommentsLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val comments = resource.data
                    commentAdapter.submitList(comments)
                }
                is Resource.Error -> {
                    Snackbar.make(
                        requireContext(),
                        binding.linearLayout,
                        resource.message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.comments.layoutManager = LinearLayoutManager(requireContext())
        binding.comments.adapter = commentAdapter
    }
}