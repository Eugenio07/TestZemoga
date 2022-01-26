package com.example.testzemoga.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentDB(
    @PrimaryKey
    val id: String
)
