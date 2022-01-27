package com.example.testzemoga.data

import com.example.domain.CommentItem
import com.example.domain.PostItem
import com.example.testzemoga.data.database.entity.PostDB
import com.example.testzemoga.data.server.networkAPI.CommentsResponse
import com.example.testzemoga.data.server.networkAPI.PostResponse

fun PostResponse.toPostDomainItem(): PostItem = PostItem(userId, id, title, body, read = id>20)

fun PostItem.toPostDB(): PostDB = PostDB(userId, id.toString(), title, body, favorite, read)

fun PostDB.toPostDomainItem(): PostItem = PostItem(userId, id.toInt(), title, body, favorite, read)

fun CommentsResponse.toCommentDomainItem(): CommentItem = CommentItem(postId, id, name, email, body)