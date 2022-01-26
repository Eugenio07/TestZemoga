package com.example.data.sources

import com.example.domain.Either
import com.example.domain.PostItem

interface RemoteDataSource {
    suspend fun getPosts(): Either<String, List<PostItem>>
}