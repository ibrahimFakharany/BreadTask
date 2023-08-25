package com.breadtask.featues.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.breadtask.base.PostItem
import com.ramcosta.composedestinations.annotation.Destination

interface HomeScreenNavigator {
    fun navigateToPostDetails(id: Long)
}

@Destination
@Composable
fun HomeScreen(navigator: HomeScreenNavigator, viewModel: HomeViewModel = hiltViewModel()) {
    val posts = viewModel.posts.collectAsLazyPagingItems()
    Box(
        modifier = Modifier.fillMaxSize().background(
            Color.White
        )
    ) {
        if (posts.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(posts) { index, post ->
                    post?.let {
                        PostItem(modifier = Modifier.clickable {
                            navigator.navigateToPostDetails(post.id)
                        }, post = post)
                    }

                    if (index < posts.itemSnapshotList.lastIndex) {
                        Spacer(modifier = Modifier.height(14.dp))
                        Divider(
                            color = Color.Black.copy(
                                alpha = .2f
                            ), thickness = 1.dp
                        )
                    }
                }
                item {
                    if (posts.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

}