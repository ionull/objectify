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
        lateinit var context: Context

        const val DEFAULT_STORE_NAME = "settings"
        private var storeName = DEFAULT_STORE_NAME

        val dataStore by lazy {
            val p = preferencesDataStore(name = storeName, produceMigrations = ::sharedPreferencesMigration)
            p.getValue(context, Context::javaClass)
        }

        private fun sharedPreferencesMigration(context: Context):List<DataMigration<Preferences>> {
            return if (keysToMigrate.isNotEmpty() || migrateAll) {
                listOf(
                    SharedPreferencesMigration(
                        produceSharedPreferences = {
                            PreferenceManager.getDefaultSharedPreferences(context)
                        },
                        keysToMigrate = keysToMigrate
                    )
                )
            } else listOf()
        }

        private val keysToMigrate = mutableSetOf<String>()
        private var migrateAll = false

        fun clear() {
            CoroutineScope(Dispatchers.IO).launch {
                dataStore.edit { it.clear() }
            }
        }

        fun init(context: Context, keysToMigrate: Set<String> = setOf(), migrateAll: Boolean = false, storeName: String = DEFAULT_STORE_NAME) {
            PreferenceLoader.context = context.applicationContext
            PreferenceLoader.keysToMigrate.addAll(keysToMigrate)
            PreferenceLoader.storeName = storeName
        }
    }
}
