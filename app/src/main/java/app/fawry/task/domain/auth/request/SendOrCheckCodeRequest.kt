package app.fawry.task.domain.auth.request

import androidx.annotation.Keep

@Keep
class SendOrCheckCodeRequest {
  var phone: String = ""
  var code: String? = null
}
