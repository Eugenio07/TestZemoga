package com.example.testzemoga.data.server.networkAPI

import com.example.testzemoga.data.server.getRetrofit
import retrofit2.http.GET

const val ROOT_URL = "https://jsonplaceholder.typicode.com/"

const val GET_POSTS = "/posts"

interface NetworkService{
    @GET(GET_POSTS)
    suspend fun getPosts():List<PostResponse>

}


object NetworkAPI{
    val RETROFIT_SERVICE: NetworkService by lazy{
        getRetrofit(ROOT_URL).create(NetworkService::class.java)
    }
}