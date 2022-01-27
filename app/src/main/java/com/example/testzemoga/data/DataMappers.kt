package com.example.testzemoga.data

import com.example.domain.CommentItem
import com.example.domain.PostItem
import com.example.testzemoga.data.server.networkAPI.CommentsResponse
import com.example.testzemoga.data.server.networkAPI.PostResponse

fun PostResponse.toPostDomainItem(): PostItem = PostItem(userId, id, title, body)

fun CommentsResponse.toCommentDomainItem(): CommentItem = CommentItem(postId, id, name, email, body)