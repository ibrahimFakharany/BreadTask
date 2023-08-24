package com.breadtask.featues.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.breadtask.model.Post
import com.ramcosta.composedestinations.annotation.Destination

interface HomeScreenNavigator {
    fun navigateToPostDetails(id: Long)
}

@Destination
@Composable
fun HomeScreen(navigator: HomeScreenNavigator, viewModel: HomeViewModel = hiltViewModel()) {
    val posts = viewModel.posts.collectAsLazyPagingItems()
    Box(modifier = Modifier.fillMaxSize()) {
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
                items(posts) { post ->
                    post?.let {
                        PostItem({
                            navigator.navigateToPostDetails(post.id)
                        }, it, modifier = Modifier.fillMaxSize())
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

@Composable
fun PostItem(navigateToDetails: (postId: Long) -> Unit, post: Post, modifier: Modifier) {
    Card(modifier.padding(30.dp).clickable {
        navigateToDetails(post.id)
    }) {
        Column(
            modifier = modifier.fillMaxWidth().height(IntrinsicSize.Max).padding(30.dp)
        ) {
            Text(post.id.toString(), style = TextStyle(fontSize = 20.sp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(post.userId.toString(), style = TextStyle(fontSize = 16.sp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(post.title.toString(), style = TextStyle(fontSize = 16.sp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(post.body.toString(), style = TextStyle(fontSize = 16.sp))
        }
    }

}