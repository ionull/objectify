package bz.tsung.android.objectify;

import android.content.Context;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by tsung on 3/18/14.
 */
public class ObjectPreferenceLoader<T> {
    StringPreferenceLoader stringPreferenceLoader;
    Type clazz;
    Context context;
    final static Gson gson = new Gson();

    public ObjectPreferenceLoader(Context context, final String key, Type clazz) {
        this.context = context;
        this.clazz = clazz;
        stringPreferenceLoader = new StringPreferenceLoader(context, key);
    }

    public T load() throws NoSuchPreferenceFoundException {
        String value = stringPreferenceLoader.load();
        try {
            return gson.fromJson(value, clazz);
        } catch (Exception e) {
            throw new NoSuchPreferenceFoundException();
        }
    }

    public void save(T value) {
        stringPreferenceLoader.save(gson.toJson(value));
    }

    public void remove() {
        stringPreferenceLoader.remove();
    }

    public static void clear(Context context) {
        StringPreferenceLoader.clear(context);
    }
}
