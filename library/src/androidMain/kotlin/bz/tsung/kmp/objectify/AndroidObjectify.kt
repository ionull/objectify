package bz.tsung.kmp.objectify

import android.annotation.SuppressLint
import android.content.Context
import android.preference.PreferenceManager
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import bz.tsung.kmp.objectify.PreferenceLoader.Companion.DEFAULT_STORE_NAME
import bz.tsung.kmp.objectify.PreferenceLoader.Companion.keysToMigrate
import bz.tsung.kmp.objectify.PreferenceLoader.Companion.migrateAll
import bz.tsung.kmp.objectify.PreferenceLoader.Companion.storeName

@SuppressLint("StaticFieldLeak")
object AndroidObjectify {
    private lateinit var _context: Context

    fun sharedPreferencesMigration(context: Context): List<DataMigration<Preferences>> {
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

    fun init(context: Context, keysToMigrate: Set<String> = setOf(), migrateAll: Boolean = false, storeName: String = DEFAULT_STORE_NAME) {
        _context = context.applicationContext
        PreferenceLoader.init(keysToMigrate, migrateAll, storeName) {
            createDataStore()
        }
    }

    private fun createDataStore(): DataStore<Preferences> {
        val p = preferencesDataStore(name = storeName, produceMigrations = ::sharedPreferencesMigration)
        return p.getValue(_context, Context::javaClass)
    }
}
