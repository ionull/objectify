package bz.tsung.android.objectify

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Created by tsung on 3/31/14.
 */
class StringPreferenceLoader(context: Context, key: String) :
    PreferenceLoader<String>(context, key) {
    override val dataStoreKey get() = stringPreferencesKey(key)
}
