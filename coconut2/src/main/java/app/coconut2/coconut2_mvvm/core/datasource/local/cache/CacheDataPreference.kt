package app.coconut2.coconut2_mvvm.core.datasource.local.cache

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import app.coconut2.coconut2_mvvm.core.DataType
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.flow.Flow
import java.util.Date

abstract class CacheDataPreference<T>(private val sharedPreferences: SharedPreferences) {
    abstract fun getKeyName(): String
    abstract fun getValue(json: String): T
    open fun expiredInterval() = 0 // in second

    private fun getKeyTimeName() = getKeyName().plus("_expired")

    open fun save(data: T?, type: DataType = DataType.NON_JSON) {
        if (data == null) return

        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val valueOfCache = let{
            when(type) {
                DataType.JSON -> ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(data)
                else -> data.toString()
            }
        }
        editor.putString(
            getKeyName(),
            valueOfCache
        )
        if (expiredInterval() > 0) {
            editor.putLong(getKeyTimeName(), Date().time)
        }
        editor.apply()
    }

    open fun get(): Flow<T> {
        val json = sharedPreferences.getString(getKeyName(), null)
        if (json.isNullOrEmpty()) return MutableLiveData<T>().apply { postValue(null) }.asFlow()

        if (expiredInterval() > 0) {
            val lastUpdate = sharedPreferences.getLong(getKeyTimeName(), 0)
            if (lastUpdate > 0) {
                val expiredTime = lastUpdate + expiredInterval() * 1000
                if (Date().time >= expiredTime) {
                    return MutableLiveData<T>().apply { postValue(null) }.asFlow()
                }
            }
        }

        return MutableLiveData<T>().apply { postValue(getValue(json)) }.asFlow()
    }

    open fun clear() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove(getKeyName())
        editor.apply()
    }

    open fun getCached(): T? {
        val json = sharedPreferences.getString(getKeyName(), null)
        if (json.isNullOrEmpty()) return null

        if (expiredInterval() > 0) {
            val lastUpdate = sharedPreferences.getLong(getKeyTimeName(), 0)
            if (lastUpdate > 0) {
                val expiredTime = lastUpdate + expiredInterval() * 1000
                if (Date().time >= expiredTime) {
                    return null
                }
            }
        }

        return getValue(json)
    }
}