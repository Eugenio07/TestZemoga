package com.example.testzemoga.ui.main

import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.PostItem
import com.example.testzemoga.databinding.PostItemBinding
import com.example.testzemoga.ui.main.PostsAdapter.PostHolder

class PostsAdapter(private val postList: List<PostItem>, private val clickListener: PostListener) :
    ListAdapter<PostItem, PostHolder>(CartDiffCallback()){

    class PostHolder(private val binding: PostItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun render(item: PostItem, clickListener: PostListener){
                binding.post = item
                binding.postTitle.text = item.title
                binding.postUser.text = item.userId.toString()
                binding.imUnread.visibility = if (item.read) INVISIBLE else VISIBLE
                binding.imFavorite.visibility = if(item.favorite) VISIBLE else INVISIBLE
                binding.clickListener = clickListener
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(layoutInflater, parent, false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.render(postList[position], clickListener)
    }

    override fun getItemCount(): Int = postList.size
}

class CartDiffCallback: DiffUtil.ItemCallback<PostItem>() {
    override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
        return oldItem == newItem
    }

}

class PostListener(val clickListener: (post: PostItem) -> Unit){
    fun onClick(post: PostItem) = clickListener(post)
}