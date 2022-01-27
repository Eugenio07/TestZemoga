package com.example.data.repositories

import com.example.data.sources.LocalDataSource
import com.example.data.sources.RemoteDataSource
import com.example.domain.CommentItem
import com.example.domain.Either
import com.example.domain.PostItem

class PostRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getPosts() : Either<String, List<PostItem>> {
        return remoteDataSource.getPosts()
    }

    suspend fun getComments(postID: String) : Either<String, List<CommentItem>>{
        return remoteDataSource.getComments(postID)
    }
}