package com.example.testzemoga.data

import com.example.domain.PostItem
import com.example.testzemoga.data.server.networkAPI.PostResponse

fun PostResponse.toPostDomainItem(): PostItem = PostItem(userId, id, title, body)