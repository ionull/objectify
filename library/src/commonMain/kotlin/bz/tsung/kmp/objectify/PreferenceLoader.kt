package bz.tsung.kmp.objectify

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
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
abstract class PreferenceLoader<T>(val key: String) {
    abstract val dataStoreKey: Preferences.Key<T>

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
            setSuspend(value)
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
        const val DEFAULT_STORE_NAME = "settings"
        var storeName = DEFAULT_STORE_NAME

        lateinit var dataStore: DataStore<Preferences>
        /*
        val dataStore by lazy {
            createDataStore()
        }
         */

        val keysToMigrate = mutableSetOf<String>()
        var migrateAll = false

        fun clear() {
            CoroutineScope(Dispatchers.IO).launch {
                dataStore.edit { it.clear() }
            }
        }

        fun init(keysToMigrate: Set<String> = setOf(), migrateAll: Boolean = false, storeName: String = DEFAULT_STORE_NAME, createDataStore: () -> DataStore<Preferences>) {
            Companion.migrateAll = migrateAll
            Companion.keysToMigrate.addAll(keysToMigrate)
            Companion.storeName = storeName
            dataStore = createDataStore()
        }
    }
}
