package com.breadtask.remote

import com.breadtask.data.IRemoteDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val service: PostsService) : IRemoteDataSource {
    override suspend fun fetchPosts(page: Int?, perPage: Int?) = service.getPosts(page, perPage)
    override suspend fun getCommentsForPost(postId: Long, page: Int?, perPage: Int?) =
        service.getCommentsForPost(postId, page, perPage)

}