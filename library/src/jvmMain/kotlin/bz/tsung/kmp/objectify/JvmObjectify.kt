package bz.tsung.kmp.objectify

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import bz.tsung.kmp.objectify.PreferenceLoader.Companion.DEFAULT_STORE_NAME
import bz.tsung.kmp.objectify.PreferenceLoader.Companion.storeName
import java.io.File

object JvmObjectify {
    lateinit var _storePath: String

    //fun init(storePath: String, keysToMigrate: Set<String> = setOf(), migrateAll: Boolean = false, storeName: String = DEFAULT_STORE_NAME) {
    fun init(storePath: String, storeName: String = DEFAULT_STORE_NAME) {
        _storePath = storePath
        //PreferenceLoader.init(keysToMigrate, migrateAll, storeName)
        PreferenceLoader.init(storeName = storeName) {
            createDataStore()
        }
    }

    private fun createDataStore(): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(produceFile = { File(_storePath, "$storeName.preferences_pb") })
    }
}
