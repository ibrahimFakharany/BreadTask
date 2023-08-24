package com.breadtask.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.breadtask.model.Post
import com.breadtask.model.PostRemoteKeys

const val PER_PAGE = 10

@OptIn(ExperimentalPagingApi::class)
class PostsRemoteMediator constructor(
    private val remoteDataSource: IRemoteDataSource, private val localDataSource: ILocalDataSource
) : RemoteMediator<Int, Post>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Post>): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = remoteDataSource.fetchPosts(page = currentPage, perPage = PER_PAGE)
            val endOfPaginationReached = response.isEmpty()

            val nextPage = if (endOfPaginationReached) null else currentPage + 1
            val keys = response.map { unsplashImage ->
                PostRemoteKeys(
                    id = unsplashImage.id.toString(), nextPage = nextPage
                )
            }
            localDataSource.saveData(
                isRefresh = loadType == LoadType.REFRESH, keys = keys, posts = response
            )
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Post>
    ): PostRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                localDataSource.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Post>
    ): PostRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { post ->
            localDataSource.getRemoteKeys(id = post.id)
        }
    }

}