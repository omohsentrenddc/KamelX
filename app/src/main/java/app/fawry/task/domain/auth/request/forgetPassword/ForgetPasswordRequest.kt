package app.fawry.task.domain.auth.request.forgetPassword

import androidx.annotation.Keep

@Keep
class ForgetPasswordRequest {
  var phone: String? = null
  var email: String? = null
  var code: String? = null
}
