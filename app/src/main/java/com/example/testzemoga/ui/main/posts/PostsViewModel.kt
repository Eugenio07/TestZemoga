package com.example.testzemoga.ui.main.posts

import androidx.lifecycle.ViewModel
import com.example.domain.Either
import com.example.interactivemovies.ScopedViewModel
import com.example.usecases.PostUseCases
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor (private val postUseCases: PostUseCases, uiDispatcher: CoroutineDispatcher) : ScopedViewModel(uiDispatcher) {

    init {
        initScope()
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
                }
            }
        }
    }
}