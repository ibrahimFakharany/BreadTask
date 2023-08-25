package com.breadtask.features.postdetails

import com.breadtask.base.ViewState
import com.breadtask.model.Post

class PostDetailsContract {
    data class PostDetailsState(val post: Post? = null) : ViewState

}