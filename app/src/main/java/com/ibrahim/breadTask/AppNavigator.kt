package com.ibrahim.breadTask

import androidx.navigation.NavHostController
import com.breadtask.featues.home.HomeScreenNavigator
import com.breadtask.features.postdetails.destinations.PostDetailsScreenDestination
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigateTo

class AppNavigator(val navigator: NavHostController) : HomeScreenNavigator {
    override fun navigateToPostDetails(id: Long) {
        navigator.navigateTo(PostDetailsScreenDestination() within NavGraph.postsGraph)
    }
}