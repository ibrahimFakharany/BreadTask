package com.breadtask.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.breadtask.model.Post

@Dao
interface PostsDao {
    @Insert(onConflict = REPLACE)
    suspend fun savePosts(posts: List<Post>)

    @Query("SELECT * FROM post_table")
    fun getPosts(): PagingSource<Int, Post>

    @Query("DELETE FROM post_table")
    fun clearAll()

    @Query("SELECT * FROM post_table WHERE id=:id")
    suspend fun getPostById(id: Long): Post
}