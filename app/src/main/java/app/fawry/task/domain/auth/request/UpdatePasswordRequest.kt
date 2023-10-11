package app.fawry.task.domain.auth.request

import androidx.annotation.Keep

@Keep
class UpdatePasswordRequest {
  var phone: String? = null
  var email: String? = null
  var oldPassword: String = ""
  var password: String = ""
  var password_confirmation: String = ""
}
