package com.example.testzemoga.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.domain.Either
import com.example.interactivemovies.ScopedViewModel
import com.example.usecases.PostUseCases
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor (private val postUseCases: PostUseCases, uiDispatcher: CoroutineDispatcher) : ScopedViewModel(uiDispatcher) {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    init {
        println("init vm")
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

    fun setIndex(index: Int) {
        _index.value = index
    }
}