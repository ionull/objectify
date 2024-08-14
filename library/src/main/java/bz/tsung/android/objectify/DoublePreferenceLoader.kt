package bz.tsung.android.objectify

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey

/**
 * Created by tsung on 6/12/14.
 */
class DoublePreferenceLoader(key: String) :
    PreferenceLoader<Double>(key) {
    override val dataStoreKey: Preferences.Key<Double>
        get() = doublePreferencesKey(key)
}
