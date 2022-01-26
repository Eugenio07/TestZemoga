package com.example.usecases

import com.example.data.repositories.PostRepository
import com.example.domain.Either
import com.example.domain.PostItem

class PostUseCases(private val postRepository: PostRepository) {
    suspend fun getPosts(): Either<String, List<PostItem>> = postRepository.getPosts()
}