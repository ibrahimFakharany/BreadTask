package com.breadtask.featues.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
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
        ), contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = posts, key = { post ->
                post.id
            }) { item ->
                item?.let { post ->
                    PostItem(modifier = Modifier.clickable {
                        navigator.navigateToPostDetails(post.id)
                    }, post = post)
                    Spacer(modifier = Modifier.size(20.dp))
                }

            }
            if (posts.loadState.refresh is LoadState.Error && posts.itemCount > 0) item {
                AppendErrorScreen {
                    posts.retry()
                }
            }
            when (posts.loadState.append) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> item {
                    CircularProgressIndicator()
                }

                is LoadState.Error -> item {
                    AppendErrorScreen {
                        posts.retry()
                    }
                }

            }
        }
        when (posts.loadState.refresh) {
            is LoadState.Loading -> {
                if (posts.itemCount == 0) CircularProgressIndicator()
            }

            is LoadState.Error -> {
                if (posts.itemCount == 0) RefreshErrorScreen { posts.retry() }
            }

            else -> Unit
        }

    }


}


@Composable
fun RefreshErrorScreen(retry: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("There is no internet connection")
        Button(onClick = {
            retry.invoke()

        }) {
            Text("Retry")
        }
    }
}

@Composable
fun AppendErrorScreen(retry: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("There is no internet connection")
        Button(onClick = {
            retry.invoke()
        }) {
            Text("Retry")
        }
    }
}