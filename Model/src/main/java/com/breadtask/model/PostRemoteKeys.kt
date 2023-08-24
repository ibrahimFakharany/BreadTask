package com.breadtask.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_remote_keys")
data class PostRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nextPage: Int?
)