package bz.tsung.android.objectify;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * Created by tsung on 3/31/14.
 */
public class StringPreferenceLoader extends PreferenceLoader {
    public StringPreferenceLoader(Context context, String key) {
        super(context, key);
    }

    public String load() throws NoSuchPreferenceFoundException {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String value = appSharedPrefs.getString(getKey(), "");
        if(TextUtils.isEmpty(value)) {
            throw new NoSuchPreferenceFoundException();
        }
        return value;
    }

    public void save(String value) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(getKey(), value);
        prefsEditor.commit();
    }
}
