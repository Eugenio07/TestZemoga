package com.example.testzemoga.data.database.db

import com.example.data.sources.LocalDataSource

class RoomDataSource(dataBase: AppDataBase): LocalDataSource {
    private val postDao = dataBase.PostDao()
    private val commentDao = dataBase.CommentDao()
}