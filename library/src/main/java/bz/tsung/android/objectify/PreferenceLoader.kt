package bz.tsung.android.objectify

import android.content.Context
import android.preference.PreferenceManager
import androidx.datastore.core.DataMigration
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by tsung on 5/19/14.
 */
abstract class PreferenceLoader<T>(val context: Context, val key: String) {
    abstract val dataStoreKey: Preferences.Key<T>

    private val dataStore get() = context.applicationContext.dataStore

    fun get(): T? {
        return runBlocking {
            dataStore.data.map { it[dataStoreKey] }.firstOrNull()
        }
    }

    fun getOrDefault(default: T): T {
        return get()?:default
    }

    fun flow(): Flow<T?> {
        return dataStore.data.map { it[dataStoreKey] }
    }

    fun set(value: T) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { it[dataStoreKey] = value }
        }
    }

    suspend fun setSuspend(value: T) {
        dataStore.edit { it[dataStoreKey] = value }
    }

    fun remove() {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit { it.remove(dataStoreKey) }
        }
    }

    companion object {
        val Context.dataStore by preferencesDataStore(name = "settings", produceMigrations = ::sharedPreferencesMigration)

        private fun sharedPreferencesMigration(context: Context):List<DataMigration<Preferences>> {
            return listOf(
                SharedPreferencesMigration(
                    produceSharedPreferences = {
                        PreferenceManager.getDefaultSharedPreferences(context)
                    },
                    keysToMigrate = keysToMigrate
                )
            )
        }

        private val keysToMigrate = mutableSetOf<String>()
        fun clear(context: Context?) {
            CoroutineScope(Dispatchers.IO).launch {
                context?.applicationContext?.dataStore?.edit { it.clear() }
            }
        }

        fun init(keysToMigrate: Set<String>) {
            PreferenceLoader.keysToMigrate.addAll(keysToMigrate)
        }
    }
}
