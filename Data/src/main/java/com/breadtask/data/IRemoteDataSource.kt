package com.breadtask.data

import com.breadtask.model.Comment
import com.breadtask.model.Post

interface IRemoteDataSource {
    suspend fun fetchPosts(page: Int?, perPage: Int?): List<Post>
    suspend fun getCommentsForPost(postId: Int, page: Int?, perPage: Int?): List<Comment>
}