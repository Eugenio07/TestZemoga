package com.example.testzemoga.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Either
import com.example.domain.Event
import com.example.domain.PostItem
import com.example.testzemoga.ScopedViewModel
import com.example.testzemoga.ui.main.posts.PostsViewModel.PostsModel.showPostList
import com.example.usecases.PostUseCases
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor (private val postUseCases: PostUseCases, uiDispatcher: CoroutineDispatcher) : ScopedViewModel(uiDispatcher) {


    private val _model = MutableLiveData<Event<PostsModel>>()
    val model: LiveData<Event<PostsModel>>
        get() = _model


    sealed class PostsModel {
        data class showPostList(val postsList: List<PostItem>) : PostsModel()
    }

    init {
        initScope()
        updatePosts()
    }

    fun updatePosts(){
        println("updatePosts")
        launch {
            when (val response =  postUseCases.getPosts()) {
                is Either.Left -> {
                    Logger.d("error en la API: ${response.l}")
                }
                is Either.Right -> {
                    Logger.d("Prueba post: ${response.r[0]}")
                    _model.value = Event(showPostList(response.r))
                }
            }
        }
    }
}