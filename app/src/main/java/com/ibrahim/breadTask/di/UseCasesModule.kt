package com.ibrahim.breadTask.di

import com.breadtask.domain.GetPostsUseCase
import com.breadtask.domain.IPostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {
    @Provides
    fun provideGetPostsUseCase(
        postsRepository: IPostsRepository,
    ): GetPostsUseCase = GetPostsUseCase(postsRepository)
}