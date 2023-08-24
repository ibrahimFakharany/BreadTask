package com.breadtask.features.postdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun PostDetailsScreen() {
    PostDetailsScreen(viewModel = hiltViewModel())
}

@Composable
fun PostDetailsScreen(viewModel: PostDetailsViewModel) {
    val comments = viewModel.getComments().collectAsLazyPagingItems()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            when (comments.loadState.refresh) {
                is LoadState.NotLoading -> {
                    if (comments.itemCount == 0) {
                        item {
                            ErrorView()

                        }
                    } else {
                        items(items = comments) {
                            it?.let { item ->
                                Text(item.body)
                                Spacer(modifier = Modifier.size(20.dp))
                            }
                        }
                    }
                }

                is LoadState.Loading -> item { CircularProgressIndicator() }
                is LoadState.Error -> item {
                    ErrorView()
                }
            }
            when (comments.loadState.append) {
                is LoadState.NotLoading -> Unit
                is LoadState.Loading -> item { CircularProgressIndicator() }
                is LoadState.Error -> item {
                    ErrorView()

                }
            }
        }
    }
}

@Composable
fun ErrorView() {
    Text("Error", style = TextStyle(color = Color.Red))
}