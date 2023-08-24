package com.breadtask.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "post_table")
data class Comment(
    @PrimaryKey(autoGenerate = true) val tableId: Int = 0,
    val id: Long,
    @SerialName("post_id") val postId: Long,
    val name: String,
    val email: String,
    val body: String,
)