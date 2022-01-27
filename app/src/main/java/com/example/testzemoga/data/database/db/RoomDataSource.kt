package com.example.testzemoga.data.database.db

import com.example.data.sources.LocalDataSource
import com.example.domain.PostItem
import com.example.testzemoga.data.toPostDB
import com.example.testzemoga.data.toPostDomainItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(dataBase: AppDataBase) : LocalDataSource {
    private val postDao = dataBase.PostDao()
    private val commentDao = dataBase.CommentDao()

    override suspend fun postListIsEmpty(): Boolean =
        withContext(Dispatchers.IO) { postDao.postsCount() == 0 }

    override suspend fun favoritesPostListIsEmpty(): Boolean =
        withContext(Dispatchers.IO) { postDao.favoritesPostsCount() == 0 }

    override suspend fun insertPosts(posts: List<PostItem>) {
        withContext(Dispatchers.IO) { postDao.insertPosts(posts.map { it.toPostDB() }) }
    }

    override suspend fun deleteAllPosts(/*posts: List<PostItem>*/) {
        withContext(Dispatchers.IO) { postDao.deleteAllPosts(/*posts.map { it.toPostDB() }*/) }
    }

    override suspend fun deletePost(post: PostItem) {
        withContext(Dispatchers.IO) { postDao.deletePost(post.toPostDB()) }
    }

    override suspend fun getAllPosts(): List<PostItem> =
        withContext(Dispatchers.IO) { postDao.getAllPosts().map { it.toPostDomainItem() } }

    override suspend fun getFavoritesPosts(): List<PostItem> =
        withContext(Dispatchers.IO) { postDao.getFavoritesPosts().map { it.toPostDomainItem() } }

    override suspend fun getPostByID(postID: String): PostItem =
        withContext(Dispatchers.IO) { postDao.getPostByID(postID).toPostDomainItem() }

    override suspend fun updatePost(post: PostItem) {
        withContext(Dispatchers.IO) { postDao.updatePost(post.toPostDB()) }
    }
}