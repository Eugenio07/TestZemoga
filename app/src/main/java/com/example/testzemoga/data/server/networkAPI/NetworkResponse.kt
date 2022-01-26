package com.example.testzemoga.data.server.networkAPI

import com.example.testzemoga.data.server.networkAPI.CommentsResponse.CommentsResponseItem

data class PostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)


//data class CategoryResponse(
//    val categories: List<Category>
//) {
//    data class Category(
//        val idCategory: String?,
//        val strCategory: String?,
//        val strCategoryThumb: String?,
//        val strCategoryDescription: String?
//    )
//}

class CommentsResponse : ArrayList<CommentsResponseItem>() {
    data class CommentsResponseItem(
        val postId: Int,
        val id: Int,
        val name: String,
        val email: String,
        val body: String
    )
}