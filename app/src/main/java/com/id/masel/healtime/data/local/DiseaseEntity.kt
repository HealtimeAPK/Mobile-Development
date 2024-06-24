package com.id.masel.healtime.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "stories")
data class DiseaseEntity(
    @PrimaryKey
    @ColumnInfo(name = "email")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "symptom")
    val symptom: String,

    @ColumnInfo(name = "lat")
    val lat: Double? = null,

    @ColumnInfo(name = "lon")
    val lon: Double? = null,
) : Parcelable
