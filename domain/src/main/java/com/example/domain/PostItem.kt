package com.example.domain

data class PostItem(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    var favorite: Boolean = false,
    var read: Boolean = false
)
