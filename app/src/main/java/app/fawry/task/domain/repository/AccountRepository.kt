package app.fawry.task.domain.repository

import app.fawry.task.domain.auth.entity.UserModel

interface AccountRepository {


  fun isLoggedIn(): Boolean

  fun saveUserToLocal(user: UserModel)
  fun saveKey(key: String,value:String)
  fun getKeyFromLocal(key: String): String

  fun getKeyIntFromLocal(key: String) : Int
  fun getUserToLocal(): UserModel

  fun clearUser()


}