package com.example.testzemoga.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostDB(
val userId: Int,
@PrimaryKey
val id: String,
val title: String,
val body: String,
var favorite: Boolean = false,
var read: Boolean = false
)
