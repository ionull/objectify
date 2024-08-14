package bz.tsung.android.objectify

import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Created by tsung on 3/31/14.
 */
class StringPreferenceLoader(key: String) :
    PreferenceLoader<String>(key) {
    override val dataStoreKey get() = stringPreferencesKey(key)
}
