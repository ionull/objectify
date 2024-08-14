package bz.tsung.android.objectify

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey

/**
 * Created by tsung on 6/12/14.
 */
class IntPreferenceLoader(key: String) :
    PreferenceLoader<Int>(key) {
    override val dataStoreKey: Preferences.Key<Int>
        get() = intPreferencesKey(key)
}
