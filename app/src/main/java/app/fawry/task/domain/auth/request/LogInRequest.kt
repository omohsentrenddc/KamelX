package app.fawry.task.domain.auth.request

import androidx.annotation.Keep

@Keep
class LogInRequest {
  var phone: String = ""
  var password: String = ""
  var firebase_token: String = ""
  var device_id: String = ""
}
