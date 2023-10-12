package app.fawry.task.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import app.fawry.task.domain.auth.entity.UserModel
import app.fawry.task.presentation.base.utils.Constants
import com.google.gson.Gson
import javax.inject.Inject

class AppPreferences @Inject constructor(private val context: Context) {

  private val STORE_NAME = "app_data_store"

  private val USER_DATA = Pair("USER_DATA", "")

  private val appPreferences: SharedPreferences =
    context.getSharedPreferences(Constants.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)

  private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = edit()
    operation(editor)
    editor.apply()
  }

  fun getIsLoggedIn(): Boolean {
    Log.d(TAG, "getIsLoggedIn: ${appPreferences.getInt("id",-1)}")
    return appPreferences.getInt("id", -1) != -1
  }

  fun saveUser(user: UserModel) {
    appPreferences.edit {
      it.putString(USER_DATA.first, Gson().toJson(user))
      it.putString(Constants.TOKEN, user.jwtToken)
      it.putInt("id", user.id)
      it.apply()
    }
  }

  private  val TAG = "AppPreferences"
  fun getUser(): UserModel {
    val value: String? = appPreferences.getString(USER_DATA.first, USER_DATA.second)
    Log.d(TAG, "getUser: $value")
    if (value != null && value.isNotEmpty())
      return Gson().fromJson(value, UserModel::class.java)
    else return UserModel()
  }


  fun clearUser() {
    appPreferences.edit {
      it.putString(USER_DATA.first, "")
      it.putString(Constants.TOKEN, "")
      it.putInt("id", -1)
      it.apply()
    }
  }

  fun saveKey(key: String, value: String) {
    appPreferences.edit {
      it.putString(key, value)
    }
  }

  fun getKey(key: String): String {
    return appPreferences.getString(key, "").toString()
  }

  fun getKeyInt(key: String): Int {
    return appPreferences.getInt(key, -1)
  }

  fun clearPreferences() {
    appPreferences.edit {
      it.clear().apply()
    }
  }

}