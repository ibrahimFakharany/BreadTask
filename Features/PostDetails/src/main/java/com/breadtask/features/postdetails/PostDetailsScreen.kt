package com.breadtask.features.postdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.breadtask.base.PostItem
import com.breadtask.base.R
import com.breadtask.model.Comment
import com.ramcosta.composedestinations.annotation.Destination

@Destination(navArgsDelegate = PostDetailsNavArgs::class)
@Composable
fun PostDetailsScreen() {
    PostDetailsScreen(viewModel = hiltViewModel())
}

data class PostDetailsNavArgs(
    val postId: Long
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailsScreen(viewModel: PostDetailsViewModel) {
    val comments = viewModel.getComments().collectAsLazyPagingItems()
    val postDetails = viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            postDetails.value.post?.let {
                PostItem(modifier = Modifier.border(1.dp, Color.Black.copy(alpha = .2f)), post = it)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Surface(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    "Comments", style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (comments.loadState.refresh) {
                    is LoadState.NotLoading -> {
                        if (comments.itemCount >= 0) {
                            items(items = comments) {
                                it?.let { item ->
                                    CommentItem(modifier = Modifier, item)
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
                    is LoadState.Loading -> item {

                        CircularProgressIndicator(

                        )
                    }

                    is LoadState.Error -> item {
                        ErrorView()
                    }
                }
            }
        }
    }
}

@Composable
fun CommentItem(modifier: Modifier = Modifier, comment: Comment) {

    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp).fillMaxWidth()
    ) {
        Image(
            modifier = modifier.height(30.dp).width(30.dp), painter = painterResource(
                R.drawable.user
            ), colorFilter = ColorFilter.tint(color = Color.Gray), contentDescription = "user image"
        )
        Spacer(modifier = modifier.width(14.dp))
        Column {
            Text(comment.name, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold))
            Text(comment.body, style = TextStyle(fontSize = 13.sp))
        }
    }

}

@Composable
fun ErrorView() {
    Text("Error", style = TextStyle(color = Color.Red))
}