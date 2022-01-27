package com.example.usecases

import com.example.data.repositories.PostRepository
import com.example.domain.CommentItem
import com.example.domain.Either
import com.example.domain.PostItem

class PostUseCases(private val postRepository: PostRepository) {
    suspend fun getPostsFromWeb(): Either<String, List<PostItem>> = postRepository.getPostFromWEB()

    suspend fun getPostsFromDB(): Either<String, List<PostItem>> = postRepository.getPostFromDB()

    suspend fun getFavoritesPosts(): Either<String, List<PostItem>> = postRepository.getFavoritesPosts()

    suspend fun getPostByID(postID: String): PostItem = postRepository.getPostByID(postID)

    suspend fun deleteAllPosts(/*posts: List<PostItem>*/) = postRepository.deleteAllPosts(/*posts*/)

    suspend fun deletePost(post: PostItem) {
        postRepository.deletePost(post)
    }

    suspend fun toggleFavorite(post: PostItem) = with(post) {
        copy(favorite = !favorite).also { postRepository.updatePost(it) }
    }


    suspend fun getComments(postID: String): Either<String, List<CommentItem>> =
        postRepository.getComments(postID)
}