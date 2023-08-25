package com.breadtask.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.breadtask.model.Comment

class CommentsPagingSource(val remoteSource: IRemoteDataSource, val postId: Long) :
    PagingSource<Int, Comment>() {
    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        return try {
            val pageNumber = params.key ?: 1
            val data = remoteSource.getCommentsForPost(page = pageNumber, postId = postId, perPage = PER_PAGE)
            LoadResult.Page(
                data = data,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (data.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}