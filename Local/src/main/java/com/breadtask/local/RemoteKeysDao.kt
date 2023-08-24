package com.breadtask.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.breadtask.model.PostRemoteKeys

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = REPLACE)
    suspend fun saveKeys(keys: List<PostRemoteKeys>)

    @Query("SELECT * FROM posts_remote_keys WHERE id=:id")
    suspend fun getKeys(id: Long): PostRemoteKeys

    @Query("DELETE FROM posts_remote_keys")
    suspend fun clearAll()
}