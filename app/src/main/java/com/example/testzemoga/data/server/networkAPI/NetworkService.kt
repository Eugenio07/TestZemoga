package com.example.testzemoga.data.server.networkAPI

import com.example.testzemoga.data.server.getRetrofit
import retrofit2.http.GET
import retrofit2.http.Query

const val ROOT_URL = "https://jsonplaceholder.typicode.com/"

const val GET_POSTS = "/posts"
const val GET_COMMENTS = "/comments"

interface NetworkService{
    @GET(GET_POSTS)
    suspend fun getPosts():List<PostResponse>

    @GET(GET_COMMENTS)
    suspend fun getComments(@Query("postId") postId: String):List<CommentsResponse>

}


object NetworkAPI{
    val RETROFIT_SERVICE: NetworkService by lazy{
        getRetrofit(ROOT_URL).create(NetworkService::class.java)
    }
}