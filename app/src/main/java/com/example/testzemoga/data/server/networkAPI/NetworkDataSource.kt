package com.example.testzemoga.data.server.networkAPI

import com.example.data.sources.RemoteDataSource
import com.example.domain.Either
import com.example.domain.PostItem
import com.example.testzemoga.data.toPostDomainItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class NetworkDataSource : RemoteDataSource {
    override suspend fun getPosts(): Either<String, List<PostItem>> =
        withContext(Dispatchers.IO) {
            try {
                println("Paso por aca")
                val response = NetworkAPI.RETROFIT_SERVICE.getPosts().map { it.toPostDomainItem() }
                Either.Right(response)
            } catch (e: HttpException) {
                Either.Left("Connection failure")
            } catch (e: Exception) {
                Either.Left(e.message ?: "Connection failure")
            }
        }

}