package com.example.data.sources

import com.example.domain.PostItem

interface LocalDataSource {
    suspend fun postListIsEmpty(): Boolean
    suspend fun favoritesPostListIsEmpty(): Boolean
    suspend fun insertPosts(posts: List<PostItem>)
    suspend fun deleteAllPosts(posts: List<PostItem>)
    suspend fun deletePost(post: PostItem)
    suspend fun getAllPosts(): List<PostItem>
    suspend fun getFavoritesPosts(): List<PostItem>
    suspend fun getPostByID(postID: String): PostItem
    suspend fun updatePost(post: PostItem)
}