package com.ibrahim.breadTask.di

import android.content.Context
import com.breadtask.data.ILocalDataSource
import com.breadtask.local.LocalDataSource
import com.breadtask.local.PostsDao
import com.breadtask.local.PostsDatabase
import com.breadtask.local.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {
    @Provides
    fun provideRemoteDataSource(
        postsDao: PostsDao,
        remoteKeysDao: RemoteKeysDao,
        database: PostsDatabase,
    ): ILocalDataSource = LocalDataSource(database, postsDao, remoteKeysDao)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PostsDatabase {
        return PostsDatabase.create(context)
    }

    @Provides
    fun providePostsDao(database: PostsDatabase): PostsDao {
        return database.getPostsDao()
    }

    @Provides
    fun provideRemoteKeysDao(database: PostsDatabase): RemoteKeysDao {
        return database.getRemoteKeysDao()
    }
}