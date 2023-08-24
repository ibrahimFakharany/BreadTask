package com.breadtask.features.postdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.breadtask.domain.GetCommentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(val getCommentsUseCase: GetCommentsUseCase) :
    ViewModel() {
    fun getComments() = getCommentsUseCase(64189)
    /*.cachedIn(viewModelScope)*/
}