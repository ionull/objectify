package bz.tsung.android.objectify;

import android.content.Context;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by tsung on 3/18/14.
 */
public class ObjectPreferenceLoader {
    StringPreferenceLoader stringPreferenceLoader;
    Type clazz;
    Context context;
    Gson gson;

    public <T> ObjectPreferenceLoader(Context context, final String key, Type clazz) {
        this(context, key, clazz, new Gson());
    }

    public <T> ObjectPreferenceLoader(Context context, final String key, Type clazz, Gson gson) {
        this.context = context;
        this.clazz = clazz;
        stringPreferenceLoader = new StringPreferenceLoader(context, key);
        this.gson = gson;
    }

    public <T> ObjectPreferenceLoader(Context context, final String key, Class<T> clazz) {
        this(context, key, clazz, new Gson());
    }

    public <T> ObjectPreferenceLoader(Context context, final String key, Class<T> clazz, Gson gson) {
        this.context = context;
        this.clazz = clazz;
        stringPreferenceLoader = new StringPreferenceLoader(context, key);
        this.gson = gson;
    }

    public <T> T get() {
        try {
            String value = stringPreferenceLoader.load();
            return gson.fromJson(value, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    public <T> void set(T value) {
        if ((clazz instanceof Class && ((Class)clazz).isInstance(value)) || clazz instanceof Type) {
            stringPreferenceLoader.save(gson.toJson(value));
        } else {
            throw new ShouldSaveSameTypeValueException();
        }
    }

    @Deprecated
    public <T> T load() throws NoSuchPreferenceFoundException {
        String value = stringPreferenceLoader.load();
        try {
            return gson.fromJson(value, clazz);
        } catch (Exception e) {
            throw new NoSuchPreferenceFoundException();
        }
    }

    @Deprecated
    public <T> void save(T value) {
        set(value);
    }

    public void remove() {
        stringPreferenceLoader.remove();
    }

    public static void clear(Context context) {
        StringPreferenceLoader.clear(context);
    }
}
