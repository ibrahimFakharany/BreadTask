package com.breadtask.domain

import androidx.paging.PagingData
import com.breadtask.model.Comment
import com.breadtask.model.Post
import kotlinx.coroutines.flow.Flow

interface IPostsRepository {
    fun getPosts(): Flow<PagingData<Post>>
    fun getComments(postId: Int): Flow<PagingData<Comment>>
}