package com.breadtask.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.breadtask.domain.IPostsRepository
import com.breadtask.model.Comment
import com.breadtask.model.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PostsRepository @Inject constructor(
    val remoteDataSource: IRemoteDataSource, val localDataSource: ILocalDataSource
) : IPostsRepository {

    override fun getPosts(): Flow<PagingData<Post>> {
        return Pager(config = PagingConfig(pageSize = PER_PAGE),
            remoteMediator = PostsRemoteMediator(remoteDataSource, localDataSource),
            pagingSourceFactory = {
                localDataSource.getPostsPagingSource()
            }).flow
    }

    override fun getComments(postId: Int): Flow<PagingData<Comment>> {
        return Pager(config = PagingConfig(pageSize = PER_PAGE),
            initialKey = 1,
            pagingSourceFactory = {
                CommentsPagingSource(remoteDataSource, postId)
            }).flow
    }
}