package com.breadtask.featues.home

import androidx.lifecycle.ViewModel
import com.breadtask.domain.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(getPostsUseCase: GetPostsUseCase) : ViewModel() {
    val posts = getPostsUseCase()
}