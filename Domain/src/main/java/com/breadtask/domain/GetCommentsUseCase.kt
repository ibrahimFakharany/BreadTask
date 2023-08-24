package com.breadtask.domain

import androidx.paging.PagingData
import com.breadtask.model.Comment
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(private val postsRepository: IPostsRepository) {

    operator fun invoke(postId: Int): Flow<PagingData<Comment>> {
        return postsRepository.getComments(postId)
    }
}