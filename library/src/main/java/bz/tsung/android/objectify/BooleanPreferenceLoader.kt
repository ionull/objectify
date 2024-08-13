package bz.tsung.android.objectify

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey

/**
 * Created by tsung on 5/19/14.
 */
class BooleanPreferenceLoader(context: Context, key: String) :
    PreferenceLoader<Boolean>(context, key) {
    override val dataStoreKey: Preferences.Key<Boolean>
        get() = booleanPreferencesKey(key)
}
