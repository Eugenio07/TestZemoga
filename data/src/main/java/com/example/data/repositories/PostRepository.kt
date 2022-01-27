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
    suspend fun getPostFromWEB(): Either<String, List<PostItem>> =
        when (val response = remoteDataSource.getPosts()) {
            is Either.Left -> Either.Left(response.l)
            is Either.Right -> {
                localDataSource.insertPosts(response.r)
                Either.Right(localDataSource.getAllPosts())
            }
        }

    suspend fun getPostFromDB(): Either<String, List<PostItem>> =
        if (localDataSource.postListIsEmpty()) Either.Left("List is empty")
        else Either.Right(localDataSource.getAllPosts())

    suspend fun getFavoritesPosts(): Either<String, List<PostItem>> =
        if (localDataSource.favoritesPostListIsEmpty()) Either.Left("Favorites list is empty")
        else Either.Right(localDataSource.getFavoritesPosts())

    suspend fun getPostByID(postID: String): PostItem = localDataSource.getPostByID(postID)

    suspend fun deleteAllPosts(/*posts: List<PostItem>*/) {
        localDataSource.deleteAllPosts(/*posts*/)
    }

    suspend fun deletePost(post: PostItem) {
        localDataSource.deletePost(post)
    }

    suspend fun updatePost(post: PostItem) {
        localDataSource.updatePost(post)
    }

    suspend fun getComments(postID: String): Either<String, List<CommentItem>> {
        return remoteDataSource.getComments(postID)
    }


}