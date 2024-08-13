package bz.tsung.android.objectify

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey

/**
 * Created by tsung on 6/12/14.
 */
class LongPreferenceLoader(context: Context, key: String) :
    PreferenceLoader<Long>(context, key) {
    override val dataStoreKey: Preferences.Key<Long>
        get() = longPreferencesKey(key)
}
