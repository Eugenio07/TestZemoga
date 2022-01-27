package com.example.testzemoga.ui.content

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.domain.Event
import com.example.testzemoga.R
import com.example.testzemoga.databinding.ContentFragmentBinding
import com.example.testzemoga.ui.content.ContentViewModel.ContentModel
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

        return binding.root
    }

    private fun changedUI(event: Event<ContentModel>) {
        event.getContentIfNotHandled()?.let { model ->
            when (model) {
                is ContentModel.showPostComments -> {
                    binding.rvComments.adapter = CommentsAdapter(model.comments)
                }
                is ContentModel.showPostContent -> TODO()
            }
        }
    }

}