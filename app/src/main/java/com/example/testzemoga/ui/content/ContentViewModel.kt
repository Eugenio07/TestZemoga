package com.example.testzemoga.ui.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.CommentItem
import com.example.domain.Either
import com.example.domain.Event
import com.example.domain.PostItem
import com.example.testzemoga.ScopedViewModel
import com.example.testzemoga.ui.content.ContentViewModel.ContentModel.*
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

    lateinit var post: PostItem

    init {
        initScope()
        updateComments()
        updateContent()
    }

    sealed class ContentModel {
        data class ShowPostContent(val post: PostItem) : ContentModel()
        data class ShowPostComments(val comments: List<CommentItem>) : ContentModel()
        data class ToggleFavorite(val favorite: Boolean): ContentModel()
    }

    private fun updateComments() {
        launch {
            when (val response = postUseCases.getComments(postID)) {
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("Prueba comment: ${response.r[0]}")
                    _model.value = Event(ShowPostComments(response.r))
                }
            }
        }
    }

    private fun updateContent() {
        launch {
            post = postUseCases.getPostByID(postID)
             _model.value = Event(ShowPostContent(post))
        }
    }

    fun toggleFavorite(){
        launch {
            post = postUseCases.toggleFavorite(post)
            _model.value = Event(ToggleFavorite(post.favorite))
        }
    }
}