package com.example.testzemoga.ui.main.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Either
import com.example.domain.Event
import com.example.domain.PostItem
import com.example.testzemoga.ScopedViewModel
import com.example.testzemoga.ui.main.favorites.FavoritesViewModel.FavoritesModel.FavoriteListIsEmpty
import com.example.testzemoga.ui.main.favorites.FavoritesViewModel.FavoritesModel.ShowFavoritesPostList
import com.example.usecases.PostUseCases
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {


    private val _model = MutableLiveData<Event<FavoritesModel>>()
    val model: LiveData<Event<FavoritesModel>>
        get() = _model


    sealed class FavoritesModel {
        data class ShowFavoritesPostList(val postsList: List<PostItem>) : FavoritesModel()
        object FavoriteListIsEmpty : FavoritesModel()
    }

    init {
        initScope()
    }

    fun updateFavoritesPosts() {
        launch {
            when (val response = postUseCases.getFavoritesPosts()) {
                is Either.Left -> {
                    _model.value = Event(FavoriteListIsEmpty)
                }
                is Either.Right -> {
                    _model.value = Event(ShowFavoritesPostList(response.r))
                }
            }
        }
    }
}