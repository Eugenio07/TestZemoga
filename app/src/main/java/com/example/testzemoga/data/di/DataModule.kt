package com.example.testzemoga.data.di

import com.example.data.repositories.PostRepository
import com.example.data.sources.LocalDataSource
import com.example.data.sources.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun postRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = PostRepository(localDataSource, remoteDataSource)
}