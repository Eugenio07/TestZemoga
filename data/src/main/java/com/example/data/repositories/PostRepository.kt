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
    suspend fun getPosts(): Either<String, List<PostItem>> {
        if (localDataSource.postListIsEmpty()) {
            when (val response = remoteDataSource.getPosts()) {
                is Either.Left -> return Either.Left(response.l)
                is Either.Right -> localDataSource.insertPosts(response.r)
            }
        }
        return Either.Right(localDataSource.getAllPosts())
    }

    suspend fun getFavoritesPosts(): Either<String, List<PostItem>>{
        return if(localDataSource.postListIsEmpty()) Either.Left("Favorites list is empty") else{
            Either.Right(localDataSource.getFavoritesPosts())
        }
    }

    suspend fun getPostByID(postID: String): PostItem = localDataSource.getPostByID(postID)

    suspend fun deleteAllPosts(posts: List<PostItem>){
        localDataSource.deleteAllPosts(posts)
    }

    suspend fun deletePost(post: PostItem){
        localDataSource.deletePost(post)
    }

    suspend fun updatePost(post: PostItem) {
        localDataSource.updatePost(post)
    }

    suspend fun getComments(postID: String): Either<String, List<CommentItem>> {
        return remoteDataSource.getComments(postID)
    }


}