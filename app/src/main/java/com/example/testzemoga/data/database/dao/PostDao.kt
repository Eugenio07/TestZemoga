package com.example.testzemoga.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.testzemoga.data.database.entity.PostDB

@Dao
interface PostDao {

    @Query("SELECT COUNT(id) FROM PostDB")
    fun postsCount(): Int

    @Insert
    fun insertPosts(posts: List<PostDB>)

    @Delete
    fun deleteAllPosts(posts: List<PostDB>)

    @Delete
    fun deletePost(post: PostDB)

    @Query("SELECT * FROM PostDB")
    fun getAllPosts(): List<PostDB>

    @Query("SELECT * FROM PostDB WHERE id = :postID")
    fun getPostByID(postID: String): PostDB
}