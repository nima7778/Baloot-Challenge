package ir.nima.balootchallenge.utils
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader

class PreferenceUtils {
    companion object {
        inline fun <reified T> parseJsonError(inputStream: InputStream): T{
            val reader: Reader = BufferedReader(InputStreamReader(inputStream))
            val gson = Gson()
            val listType = object : TypeToken<T?>() {}.type
            return gson.fromJson(reader, listType)
        }
        private var sharedPreferences: SharedPreferences? = null

        fun initPreferenceUtils(context: Context?) {
            if (sharedPreferences == null && context != null) {
                sharedPreferences =
                    context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            }
        }

        fun getStringPreference(tag: String?): String? {
            return if (sharedPreferences == null) null else sharedPreferences!!.getString(tag, null)
        }

        fun putStringPreference(tag: String?, value: String?) {
            if (sharedPreferences == null) return
            sharedPreferences!!.edit().putString(tag, value).apply()
        }

        fun clearPreference() {
            if (sharedPreferences == null) return
            sharedPreferences!!.edit().clear().apply()
        }

        fun getLongPreference(tag: String?): Long {
            return if (sharedPreferences == null) -1 else sharedPreferences!!.getLong(tag, -1)
        }

        fun putLongPreference(tag: String?, value: Long) {
            if (sharedPreferences == null) return
            sharedPreferences!!.edit().putLong(tag, value).apply()
        }

        fun getBooleanPreference(pref: String?): Boolean {
            return if (sharedPreferences == null) false else sharedPreferences!!.getBoolean(
                pref,
                false
            )
        }

        fun putBooleanPreference(pref: String?, value: Boolean) {
            if (sharedPreferences == null) return
            sharedPreferences!!.edit().putBoolean(pref, value).apply()
        }
    }
}