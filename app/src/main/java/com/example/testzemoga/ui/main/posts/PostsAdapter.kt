package com.example.testzemoga.ui.main.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.PostItem
import com.example.testzemoga.databinding.PostItemBinding
import com.example.testzemoga.ui.main.posts.PostsAdapter.PostHolder

class PostsAdapter(private val postList: List<PostItem>, private val clickListener: PostListener) :
    RecyclerView.Adapter<PostHolder>(){
    class PostHolder(private val binding: PostItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun render(item: PostItem, clickListener: PostListener){
                binding.post = item
                binding.postTitle.text = item.title
                binding.postUser.text = item.userId.toString()
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

class PostListener(val clickListener: (post: PostItem) -> Unit){
    fun onClick(post: PostItem) = clickListener(post)
}