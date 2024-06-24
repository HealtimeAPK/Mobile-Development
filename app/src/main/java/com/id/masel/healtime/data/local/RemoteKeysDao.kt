package com.id.masel.healtime.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(remoteKey: List<RemoteKeys>)

        @Query("Select * From remote_keys Where id = :id")
        suspend fun getRemoteKeys(id:String) : RemoteKeys?

        @Query("Delete From remote_keys")
        suspend fun deleteRemoteKeys()
}