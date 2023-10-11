package com.trenddc.hashksa.task.domain.auth.request

import androidx.annotation.Keep

@Keep
class LogInRequest {
  var name: String = ""
  var password: String = ""
  var firebase_token: String = ""
  var device_id: String = ""
}
