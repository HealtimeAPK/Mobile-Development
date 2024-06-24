package com.id.masel.healtime.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DiseaseDao {

    @Query("SELECT * FROM disease ORDER BY createdAt DESC")
    fun getDisease(): PagingSource<Int, DiseaseEntity>

}