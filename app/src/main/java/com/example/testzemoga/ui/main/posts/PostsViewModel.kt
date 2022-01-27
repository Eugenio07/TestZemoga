package com.example.testzemoga.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Either
import com.example.domain.Event
import com.example.domain.PostItem
import com.example.testzemoga.ScopedViewModel
import com.example.testzemoga.ui.main.favorites.FavoritesViewModel
import com.example.testzemoga.ui.main.posts.PostsViewModel.PostsModel.ListIsEmpty
import com.example.testzemoga.ui.main.posts.PostsViewModel.PostsModel.ShowPostList
import com.example.usecases.PostUseCases
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {


    private val _model = MutableLiveData<Event<PostsModel>>()
    val model: LiveData<Event<PostsModel>>
        get() = _model


    sealed class PostsModel {
        data class ShowPostList(val postsList: List<PostItem>) : PostsModel()
        object ListIsEmpty : PostsModel()
    }

    init {
        initScope()
    }

    fun updatePosts() {
        launch {
            when (val response = postUseCases.getPostsFromWeb()) {
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                    _model.value = Event(ListIsEmpty)
                }
                is Either.Right -> {
                    Logger.d("Prueba post: ${response.r[0]}")
                    _model.value = Event(ShowPostList(response.r))
                }
            }
        }
    }

    fun deleteAllPosts(){
        launch {
            postUseCases.deleteAllPosts()
            _model.value = Event(ListIsEmpty)
        }
    }

    fun findPosts() {
        launch {
            when (val response = postUseCases.getPostsFromDB()) {
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                    _model.value = Event(ListIsEmpty)
                }
                is Either.Right -> {
                    Logger.d("Prueba post: ${response.r[0]}")
                    _model.value = Event(ShowPostList(response.r))
                }
            }
        }
    }

    fun markAsRead(post: PostItem){
        launch {
            postUseCases.markAsRead(post)
        }
    }

    fun deletePost(post: PostItem){
        launch {
            postUseCases.deletePost(post)
        }
    }
}