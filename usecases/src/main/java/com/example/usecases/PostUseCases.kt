package com.example.usecases

import com.example.data.repositories.PostRepository
import com.example.domain.CommentItem
import com.example.domain.Either
import com.example.domain.PostItem

class PostUseCases(private val postRepository: PostRepository) {
    suspend fun getPosts(): Either<String, List<PostItem>> = postRepository.getPosts()

    suspend fun getComments(postID: String): Either<String, List<CommentItem>> = postRepository.getComments(postID)
}