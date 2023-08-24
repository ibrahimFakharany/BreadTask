package com.ibrahim.breadTask.di

import com.breadtask.data.ILocalDataSource
import com.breadtask.data.IRemoteDataSource
import com.breadtask.data.PostsRepository
import com.breadtask.domain.IPostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun providePostsRepository(
        remoteDataSource: IRemoteDataSource,
        localDataSource: ILocalDataSource,
    ): IPostsRepository = PostsRepository(remoteDataSource, localDataSource)

}