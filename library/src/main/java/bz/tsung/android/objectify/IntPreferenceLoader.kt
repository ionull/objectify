package bz.tsung.android.objectify

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey

/**
 * Created by tsung on 6/12/14.
 */
class IntPreferenceLoader(context: Context, key: String) :
    PreferenceLoader<Int>(context, key) {
    override val dataStoreKey: Preferences.Key<Int>
        get() = intPreferencesKey(key)
}
