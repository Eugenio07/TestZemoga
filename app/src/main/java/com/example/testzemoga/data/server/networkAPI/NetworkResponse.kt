package com.example.testzemoga.data.server.networkAPI

data class PostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

data class CommentsResponse(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)
