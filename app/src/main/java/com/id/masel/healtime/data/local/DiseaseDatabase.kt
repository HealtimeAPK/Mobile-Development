package com.id.masel.healtime.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DiseaseEntity::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class DiseaseDatabase: RoomDatabase() {
    abstract fun DiseaseDao(): DiseaseDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        private val INSTANCE: DiseaseDatabase? = null

        fun getDatabase(context: Context): DiseaseDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    DiseaseDatabase::class.java,
                    "Disease_database"
                ).build()
            }
        }
    }

}