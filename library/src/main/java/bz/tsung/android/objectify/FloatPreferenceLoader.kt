package bz.tsung.android.objectify

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.floatPreferencesKey

/**
 * Created by tsung on 6/12/14.
 */
class FloatPreferenceLoader(key: String) :
    PreferenceLoader<Float>(key) {
    override val dataStoreKey: Preferences.Key<Float>
        get() = floatPreferencesKey(key)
}
