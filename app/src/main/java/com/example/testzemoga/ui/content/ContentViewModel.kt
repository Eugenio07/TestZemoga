package com.example.testzemoga.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.CommentItem
import com.example.domain.Either
import com.example.domain.Event
import com.example.domain.PostItem
import com.example.testzemoga.ScopedViewModel
import com.example.testzemoga.ui.content.ContentViewModel.ContentModel.showPostComments
import com.example.usecases.PostUseCases
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    @Named("PostID") private val postID: String,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {


    private val _model = MutableLiveData<Event<ContentModel>>()
    val model: LiveData<Event<ContentModel>>
        get() = _model


    init {
        initScope()
        updateComments()
        updateContent()
    }

    sealed class ContentModel {
        data class showPostContent(val posts: PostItem) : ContentModel()
        data class showPostComments(val comments: List<CommentItem>) : ContentModel()
    }

    private fun updateComments() {
        launch {
            when (val response = postUseCases.getComments(postID)) {
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("Prueba comment: ${response.r[0]}")
                    _model.value = Event(showPostComments(response.r))
                }
            }
        }
    }

    private fun updateContent() {
        launch {
            val response = postUseCases.getPostByID(postID)
            Logger.d("Prueba post: $response")
            // _model.value = Event(showPostComments(response.r))
        }
    }
}