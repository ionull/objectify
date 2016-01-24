package bz.tsung.android.objectify;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by tsung on 5/19/14.
 */
public class PreferenceLoader {
    Context context;
    String key;

    public PreferenceLoader(Context context, String key) {
        this.context = context;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void remove() {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        appSharedPrefs.edit().remove(getKey()).apply();
    }

    public static void clear(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.clear();
        prefsEditor.apply();
    }
}
