package com.breadtask.domain

import com.breadtask.model.Post
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostDetailsUseCase @Inject constructor(private val postsRepository: IPostsRepository) {

    operator fun invoke(postId: Long): Flow<Post> {
        return postsRepository.getPostDetails(postId)
    }
}