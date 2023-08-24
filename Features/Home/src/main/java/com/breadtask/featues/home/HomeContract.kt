package com.breadtask.featues.home

import com.breadtask.base.ViewEvent
import com.breadtask.base.ViewSideEffect
import com.breadtask.base.ViewState
import com.breadtask.model.Post

class HomeContract {
    sealed class HomeEvent : ViewEvent {
        class OnFromClicked : HomeEvent()
        class OnToClicked : HomeEvent()
    }

    sealed class HomeState : ViewState {
        object LoadingState : HomeState()
        class LoadingSuccessState(val posts: List<Post>) : HomeState()
        object Error : HomeState()
    }

    sealed class HomeSideEffect : ViewSideEffect {
        class Error(val error: String) : HomeSideEffect()
    }
}