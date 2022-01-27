package com.example.testzemoga.ui.main.posts

import android.os.Bundle
import android.view.*
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.domain.Event
import com.example.testzemoga.R
import com.example.testzemoga.databinding.PostsFragmentBinding
import com.example.testzemoga.ui.main.PostContainerDirections
import com.example.testzemoga.ui.main.PostListener
import com.example.testzemoga.ui.main.PostsAdapter
import com.example.testzemoga.ui.main.posts.PostsViewModel.PostsModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.material.snackbar.Snackbar

import androidx.recyclerview.widget.RecyclerView

import androidx.annotation.NonNull

import androidx.recyclerview.widget.ItemTouchHelper
import com.example.domain.PostItem


@AndroidEntryPoint
class PostsFragment : Fragment() {

    private val viewModel: PostsViewModel by viewModels()
    private lateinit var binding: PostsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.posts_fragment, container, false)
        binding.viewModel = viewModel
        viewModel.findPosts()
        viewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun changedUI(event: Event<PostsModel>) {
        event.getContentIfNotHandled()?.let { model ->
            when (model) {
                is PostsModel.ShowPostList -> {
                    binding.listEmpty.visibility = INVISIBLE
                    binding.rvPosts.visibility = VISIBLE
                    binding.rvPosts.adapter = PostsAdapter(model.postsList, PostListener { post ->
                        viewModel.markAsRead(post)
                        this.findNavController().navigate(
                            PostContainerDirections.actionPostContainerToContentFragment(post.id.toString())
                        )
                    })
                    ItemTouchHelper(object :
                        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                        override fun onMove(
                            recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder
                        ): Boolean = false

                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            val post = model.postsList[viewHolder.bindingAdapterPosition]
                            binding.rvPosts.adapter!!.notifyItemRemoved(viewHolder.bindingAdapterPosition)
                            viewModel.deletePost(post)
                            Toast.makeText(
                                context,getString(R.string.post_deleted, post.id.toString()),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }).attachToRecyclerView(binding.rvPosts)
                }
                PostsModel.ListIsEmpty -> {
                    binding.rvPosts.visibility = INVISIBLE
                    binding.listEmpty.visibility = VISIBLE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.principal_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.reload -> {
                Logger.d("Update posts")
                viewModel.updatePosts()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}