package com.breadtask.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.breadtask.model.Post

@Composable
fun PostItem(modifier: Modifier = Modifier, post: Post) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ), modifier = modifier
    ) {
        Row(verticalAlignment = Alignment.Top, modifier = Modifier.padding(20.dp)) {
            Image(
                modifier = Modifier.height(50.dp).width(50.dp),
                painter = painterResource(
                    R.drawable.user
                ),
                colorFilter = ColorFilter.tint(color = Color.Gray),
                contentDescription = "user image"
            )
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    post.title, style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(14.dp))
                Text(post.body, style = TextStyle(fontSize = 14.sp))
            }

        }
    }
}