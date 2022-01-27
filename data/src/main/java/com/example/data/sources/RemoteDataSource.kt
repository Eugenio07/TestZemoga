package com.example.data.sources

import com.example.domain.CommentItem
import com.example.domain.Either
import com.example.domain.PostItem

interface RemoteDataSource {
    suspend fun getPosts(): Either<String, List<PostItem>>
    suspend fun getComments(postId: String): Either<String, List<CommentItem>>
}