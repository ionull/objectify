package bz.tsung.android.objectify;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by tsung on 6/12/14.
 */
public class LongPreferenceLoader extends PreferenceLoader {
    public LongPreferenceLoader(Context context, String key) {
        super(context, key);
    }

    @Deprecated
    public long load(long defaultValue) throws NoSuchPreferenceFoundException {
        return get(defaultValue);
    }

    @Deprecated
    public void save(long value) {
        set(value);
    }

    public long get(long defaultValue) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        long value = appSharedPrefs.getLong(getKey(), defaultValue);
        return value;
    }

    public void set(long value) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putLong(getKey(), value);
        prefsEditor.apply();
    }
}
