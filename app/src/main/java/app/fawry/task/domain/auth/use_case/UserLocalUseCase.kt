package app.fawry.task.domain.auth.use_case

import app.fawry.task.domain.auth.entity.LoginResponse
import app.fawry.task.domain.repository.AccountRepository
import app.fawry.task.domain.auth.entity.UserModel
import javax.inject.Inject

class UserLocalUseCase @Inject constructor(private val accountRepository: AccountRepository) {
  suspend operator fun invoke(user: LoginResponse) = accountRepository.saveUserToLocal(user.data)
  operator fun invoke(): UserModel = accountRepository.getUserToLocal()

  fun clearUser() {
    accountRepository.clearUser()
  }

  fun getKeyFromLocal(key: String) : String{
    return accountRepository.getKeyFromLocal(key)
  }

  fun getKeyIntFromLocal(key: String) : Int{
    return accountRepository.getKeyIntFromLocal(key)
  }

  fun saveKey(key: String,value: String){
    return accountRepository.saveKey(key,value)
  }


  fun isLoggin(): Boolean =
    accountRepository.isLoggedIn()

}