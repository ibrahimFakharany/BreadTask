package com.breadtask.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.breadtask.model.Post
import com.breadtask.model.PostRemoteKeys

@Database(
    entities = [Post::class, PostRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class PostsDatabase : RoomDatabase() {
    companion object {
        fun create(context: Context): PostsDatabase {
            val databaseBuilder =
                Room.databaseBuilder(context, PostsDatabase::class.java, "posts_database.db")
            return databaseBuilder.build()
        }
    }

    abstract fun getPostsDao(): PostsDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
}