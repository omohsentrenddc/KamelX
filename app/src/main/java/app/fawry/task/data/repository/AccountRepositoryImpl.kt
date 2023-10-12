package app.fawry.task.data.repository

import app.fawry.task.data.local.preferences.AppPreferences
import app.fawry.task.domain.auth.entity.UserModel
import app.fawry.task.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
  private val appPreferences: AppPreferences
) : AccountRepository {
  override fun isLoggedIn(): Boolean {
    return appPreferences.getIsLoggedIn()
  }
  override fun saveUserToLocal(user: UserModel) {
    appPreferences.saveUser(user)
  }

  override fun saveKey(key: String, value: String) {
    appPreferences.saveKey(key,value)
  }

  override fun getUserToLocal(): UserModel {
    return appPreferences.getUser()
  }

  override fun clearUser() {
    appPreferences.clearUser()
  }

  override fun getKeyFromLocal(key: String): String = appPreferences.getKey(key)

  override fun getKeyIntFromLocal(key: String): Int = appPreferences.getKeyInt(key)


}