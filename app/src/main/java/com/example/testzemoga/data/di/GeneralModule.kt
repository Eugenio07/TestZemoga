package com.example.testzemoga.data.di

import com.example.data.repositories.PostRepository
import com.example.usecases.PostUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class GeneralModule {
    @Provides
    fun postUseCasesProvider(postRepository: PostRepository) = PostUseCases(postRepository)

    @Provides
    fun dispatcherProvider(): CoroutineDispatcher = Dispatchers.Main
}