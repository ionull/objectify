package bz.tsung.kmp.objectify

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.byteArrayPreferencesKey

/**
 * Created by tsung on 6/12/14.
 */
class ByteArrayPreferenceLoader(key: String) :
    PreferenceLoader<ByteArray>(key) {
    override val dataStoreKey: Preferences.Key<ByteArray>
        get() = byteArrayPreferencesKey(key)
}
