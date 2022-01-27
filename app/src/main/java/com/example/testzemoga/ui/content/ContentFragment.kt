package com.example.testzemoga.ui.content

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.domain.Event
import com.example.testzemoga.R
import com.example.testzemoga.databinding.ContentFragmentBinding
import com.example.testzemoga.ui.content.ContentViewModel.ContentModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentFragment : Fragment() {

    private val viewModel: ContentViewModel by viewModels()
    private lateinit var binding: ContentFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.content_fragment, container, false)
        binding.viewModel = viewModel

        viewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun changedUI(event: Event<ContentModel>) {
        event.getContentIfNotHandled()?.let { model ->
            when (model) {
                is ContentModel.ShowPostComments -> {
                    binding.rvComments.adapter = CommentsAdapter(model.comments)
                }
                is ContentModel.ShowPostContent -> {
                    binding.postTitle.text = model.post.title
                    binding.postBody.text = model.post.body
                }
                is ContentModel.ToggleFavorite -> {
                    Toast.makeText(
                        context,
                        if (model.favorite) getString(R.string.post_added_to_favorites) else getString(
                            R.string.post_removed_to_favorites
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.content_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                viewModel.toggleFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}