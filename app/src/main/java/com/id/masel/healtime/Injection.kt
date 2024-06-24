package com.id.masel.healtime

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.id.masel.healtime.data.Repository
import com.id.masel.healtime.data.local.DiseaseDatabase

object Injection {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    fun provideRepository(context: Context): Repository {
        val preference = UserPreference(context.dataStore)
        val database = DiseaseDatabase.getDatabase(context)
        return Repository(preference, database)
    }
}