package com.trenddc.hashksa.task.domain.auth.request

import android.net.Uri
import androidx.annotation.Keep

@Keep
class UpdateProfileRequest {
  var name: String = ""
  var email: String = ""
  var phone: String = ""
  var gender_id: Int = -1
  var date_of_birth: String = ""
  var password: String = ""
  @Transient
  var uri: Uri? = null
  @Transient
  var gender: String = ""


}
