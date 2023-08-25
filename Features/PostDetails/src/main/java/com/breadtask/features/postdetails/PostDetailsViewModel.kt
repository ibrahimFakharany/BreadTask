package com.breadtask.features.postdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.breadtask.base.BaseViewModel
import com.breadtask.base.ViewEvent
import com.breadtask.base.ViewSideEffect
import com.breadtask.domain.GetCommentsUseCase
import com.breadtask.domain.GetPostDetailsUseCase
import com.breadtask.features.postdetails.destinations.PostDetailsScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val getCommentsUseCase: GetCommentsUseCase,
    val getPostDetailsUseCase: GetPostDetailsUseCase,
) : BaseViewModel<PostDetailsContract.PostDetailsState, ViewEvent, ViewSideEffect>() {
    val postId = PostDetailsScreenDestination.argsFrom(savedStateHandle).postId

    init {
        viewModelScope.launch {
            getPostDetailsUseCase(postId).flowOn(Dispatchers.IO).collect {
                setState {
                    PostDetailsContract.PostDetailsState(it)
                }
            }
        }
    }


    fun getComments() = getCommentsUseCase(postId).cachedIn(
        viewModelScope
    )

    override fun setInitialState(): PostDetailsContract.PostDetailsState =
        PostDetailsContract.PostDetailsState()

    override fun handleEvents(event: ViewEvent) {

    }
}