package com.breadtask.domain

import androidx.paging.Pager
import androidx.paging.PagingData
import com.breadtask.model.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val postsRepository: IPostsRepository) {

    operator fun invoke(): Flow<PagingData<Post>> {
        return postsRepository.getPosts()
    }
}