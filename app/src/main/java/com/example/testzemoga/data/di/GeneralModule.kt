package com.example.testzemoga.data.di

import androidx.lifecycle.SavedStateHandle
import com.example.data.repositories.PostRepository
import com.example.usecases.PostUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class GeneralModule {
    @Provides
    fun postUseCasesProvider(postRepository: PostRepository) = PostUseCases(postRepository)

    @Provides
    @Named("PostID")
    fun postIDProvider(args: SavedStateHandle): String = args.get<String>("PostID")?: throw IllegalStateException("ID null")

    @Provides
    fun dispatcherProvider(): CoroutineDispatcher = Dispatchers.Main
}