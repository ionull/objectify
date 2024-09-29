package bz.tsung.kmp.objectify

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.reflect.Type

/**
 * Created by tsung on 3/18/14.
 */
class ObjectPreferenceLoader {
    var stringPreferenceLoader: StringPreferenceLoader
    var clazz: Type
    var gson: Gson

    @JvmOverloads
    constructor(key: String, clazz: Type, gson: Gson = Gson()) {
        this.clazz = clazz
        stringPreferenceLoader = StringPreferenceLoader(key)
        this.gson = gson
    }

    fun <T> get(): T? {
        return try {
            stringPreferenceLoader.get()?.let {
                gson.fromJson<T>(it, clazz)
            }
        } catch (e: Throwable) {
            null
        }
    }

    fun <T> set(value: T) {
        if (clazz is Class<*> && (clazz as Class<*>).isInstance(value) || clazz is Type) {
            stringPreferenceLoader.set(gson.toJson(value))
        } else {
            throw ShouldSaveSameTypeValueException()
        }
    }

    fun <T> flow(): Flow<T?> {
        return stringPreferenceLoader.flow().map {
            it?.let { gson.fromJson<T>(it, clazz) }
        }
    }

    suspend fun <T> setSuspend(value: T) {
        if (clazz is Class<*> && (clazz as Class<*>).isInstance(value) || clazz is Type) {
            stringPreferenceLoader.setSuspend(gson.toJson(value))
        } else {
            throw ShouldSaveSameTypeValueException()
        }
    }

    fun remove() {
        stringPreferenceLoader.remove()
    }

    companion object {
        fun clear() {
            PreferenceLoader.clear()
        }
    }
}
