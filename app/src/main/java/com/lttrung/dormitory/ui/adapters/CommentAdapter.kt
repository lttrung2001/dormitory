package com.lttrung.dormitory.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lttrung.dormitory.databinding.LayoutMyCommentBinding
import com.lttrung.dormitory.databinding.LayoutOtherCommentBinding
import com.lttrung.dormitory.domain.data.local.room.entities.CommentLocalModel

class CommentAdapter(
    private val deleteListener: (comment: CommentLocalModel) -> Unit
) : ListAdapter<CommentLocalModel, ViewHolder>(object : DiffUtil.ItemCallback<CommentLocalModel>() {
    override fun areItemsTheSame(oldItem: CommentLocalModel, newItem: CommentLocalModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CommentLocalModel,
        newItem: CommentLocalModel
    ): Boolean {
        return oldItem == newItem
    }

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == ITEM_MY_COMMENT) {
            val binding =
                LayoutMyCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MyCommentViewHolder(binding)
        } else {
            val binding = LayoutOtherCommentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            OtherCommentViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = getItem(position)
        when (holder) {
            is MyCommentViewHolder -> {
                holder.bind(comment, deleteListener)
            }
            is OtherCommentViewHolder -> {
                holder.bind(comment)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isOwner) {
            ITEM_MY_COMMENT
        } else {
            ITEM_OTHER_COMMENT
        }
    }

    companion object {
        private const val ITEM_MY_COMMENT = 1
        private const val ITEM_OTHER_COMMENT = 2
    }
}

private class MyCommentViewHolder(val binding: LayoutMyCommentBinding) : ViewHolder(binding.root) {
    fun bind(comment: CommentLocalModel, deleteListener: (comment: CommentLocalModel) -> Unit) {
        binding.userFullName.text = comment.studentId
        binding.commentContent.text = comment.content
        binding.buttonDeleteComment.setOnClickListener { deleteListener(comment) }
    }
}

private class OtherCommentViewHolder(val binding: LayoutOtherCommentBinding) :
    ViewHolder(binding.root) {
    fun bind(comment: CommentLocalModel) {
        binding.userFullName.text = comment.studentId
        binding.commentContent.text = comment.content
    }
}