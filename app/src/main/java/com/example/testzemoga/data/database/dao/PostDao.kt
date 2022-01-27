package com.example.testzemoga.data.database.dao

import androidx.room.*
import com.example.testzemoga.data.database.entity.PostDB

@Dao
interface PostDao {

    @Query("SELECT COUNT(id) FROM PostDB")
    fun postsCount(): Int

    @Query("SELECT COUNT(id) FROM PostDB WHERE favorite = 1")
    fun favoritesPostsCount(): Int

    @Insert
    fun insertPosts(posts: List<PostDB>)

    @Delete
    fun deleteAllPosts(posts: List<PostDB>)

    @Delete
    fun deletePost(post: PostDB)

    @Query("SELECT * FROM PostDB")
    fun getAllPosts(): List<PostDB>

    @Query("SELECT * FROM PostDB WHERE favorite = 1")
    fun getFavoritesPosts(): List<PostDB>

    @Query("SELECT * FROM PostDB WHERE id = :postID")
    fun getPostByID(postID: String): PostDB

    @Update
    fun updatePost(post: PostDB)
}