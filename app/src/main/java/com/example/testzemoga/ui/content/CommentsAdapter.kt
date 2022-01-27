package com.example.testzemoga.ui.content

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.CommentItem
import com.example.testzemoga.databinding.CommentItemBinding
import com.example.testzemoga.ui.content.CommentsAdapter.CommentHolder


class CommentsAdapter(private val commentList: List<CommentItem>):
    RecyclerView.Adapter<CommentHolder>(){

    class CommentHolder(private val binding: CommentItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun render(item: CommentItem){
            binding.comment = item
            binding.commentTitle.text = item.name
            binding.commentBody.text = item.body
            binding.commentUser.text = item.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CommentItemBinding.inflate(layoutInflater, parent, false)
        return CommentHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        holder.render(commentList[position])
    }

    override fun getItemCount(): Int = commentList.size
}