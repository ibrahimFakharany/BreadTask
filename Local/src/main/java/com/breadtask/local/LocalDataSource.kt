package com.breadtask.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.breadtask.data.ILocalDataSource
import com.breadtask.model.Post
import com.breadtask.model.PostRemoteKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    val database: PostsDatabase, val postsDao: PostsDao, val keysDao: RemoteKeysDao
) : ILocalDataSource {

    override suspend fun savePosts(posts: List<Post>) {
        postsDao.savePosts(posts)
    }

    override fun getPostsPagingSource(): PagingSource<Int, Post> {
        return postsDao.getPosts()
    }

    override suspend fun saveKeys(keys: List<PostRemoteKeys>) {
        keysDao.saveKeys(keys)
    }

    override suspend fun getRemoteKeys(id: Long): PostRemoteKeys {
        return keysDao.getKeys(id)
    }

    override suspend fun getPostDetails(id: Long): Post {
        return postsDao.getPostById(id)
    }

    override suspend fun saveData(
        isRefresh: Boolean, posts: List<Post>, keys: List<PostRemoteKeys>
    ) = database.withTransaction {
        if (isRefresh) {
            keysDao.clearAll()
            postsDao.clearAll()
        }
        keysDao.saveKeys(keys)
        postsDao.savePosts(posts)
    }


    override suspend fun clearAll() {
        withContext(Dispatchers.IO) {
            postsDao.clearAll()
            keysDao.clearAll()
        }
    }

}