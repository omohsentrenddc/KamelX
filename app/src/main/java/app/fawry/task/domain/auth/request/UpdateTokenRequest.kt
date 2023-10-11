package app.fawry.task.domain.auth.request

import androidx.annotation.Keep

@Keep
class UpdateTokenRequest(
  var id: Int = 0,
  var firebase_token: String = ""
)