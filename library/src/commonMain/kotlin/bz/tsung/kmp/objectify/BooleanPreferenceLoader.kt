package bz.tsung.kmp.objectify

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey

/**
 * Created by tsung on 5/19/14.
 */
class BooleanPreferenceLoader(key: String) :
    PreferenceLoader<Boolean>(key) {
    override val dataStoreKey: Preferences.Key<Boolean>
        get() = booleanPreferencesKey(key)
}
