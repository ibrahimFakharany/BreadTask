package com.ibrahim.breadTask

import com.breadtask.featues.home.destinations.HomeScreenDestination
import com.breadtask.features.postdetails.destinations.PostDetailsScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object NavGraph {
    val postsGraph = object : NavGraphSpec {
        override val route = "posts"

        override val startRoute = HomeScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            HomeScreenDestination,
            PostDetailsScreenDestination
        ).routedIn(this).associateBy { it.route }
    }

    val root = object : NavGraphSpec {
        override val route: String = "root"

        override val startRoute: Route = postsGraph

        override val destinationsByRoute: Map<String, DestinationSpec<*>> = emptyMap()
        override val nestedNavGraphs: List<NavGraphSpec> = listOf(
            postsGraph,
        )
    }
}