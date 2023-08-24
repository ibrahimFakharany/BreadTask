package com.breadtask.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "post_table")
data class Post(
    @PrimaryKey(autoGenerate = true) val tableId: Int = 0, 
    val id: Long,
    @SerialName("user_id") val userId: Long,
    val title: String,
    val body: String,
)