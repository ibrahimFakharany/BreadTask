package com.breadtask.data

import androidx.paging.PagingSource
import com.breadtask.model.Post
import com.breadtask.model.PostRemoteKeys

interface ILocalDataSource {
    suspend fun savePosts(posts: List<Post>)
    fun getPostsPagingSource(): PagingSource<Int, Post>
    suspend fun saveKeys(keys: List<PostRemoteKeys>)
    suspend fun getRemoteKeys(id: Long): PostRemoteKeys
    suspend fun getPostDetails(id: Long): Post
    suspend fun saveData(
        isRefresh: Boolean,
        posts: List<Post>,
        keys: List<PostRemoteKeys>
    )
    suspend fun clearAll()
}