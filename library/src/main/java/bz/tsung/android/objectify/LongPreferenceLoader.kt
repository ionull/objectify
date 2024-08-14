package bz.tsung.android.objectify

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey

/**
 * Created by tsung on 6/12/14.
 */
class LongPreferenceLoader(key: String) :
    PreferenceLoader<Long>(key) {
    override val dataStoreKey: Preferences.Key<Long>
        get() = longPreferencesKey(key)
}
