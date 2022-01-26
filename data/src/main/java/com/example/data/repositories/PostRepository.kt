package com.example.data.repositories

import com.example.data.sources.LocalDataSource
import com.example.data.sources.RemoteDataSource
import com.example.domain.Either
import com.example.domain.PostItem

class PostRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getPosts() : Either<String, List<PostItem>> {
        return remoteDataSource.getPosts()
    }
}