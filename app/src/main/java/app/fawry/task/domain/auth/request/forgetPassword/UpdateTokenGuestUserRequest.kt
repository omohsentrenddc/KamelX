package com.trenddc.hashksa.task.domain.auth.request.forgetPassword

import androidx.annotation.Keep

@Keep
class UpdateTokenGuestUserRequest(
  var id: String? = null,
  var device_id: String? = null,
  var firebase_token: String = ""
)