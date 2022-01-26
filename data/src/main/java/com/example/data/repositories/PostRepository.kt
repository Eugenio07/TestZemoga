package com.example.data.repositories

import com.example.data.sources.LocalDataSource
import com.example.data.sources.RemoteDataSource

class PostRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
}