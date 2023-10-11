package com.trenddc.hashksa.task.domain.auth.request

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
class RegisterRequest : Parcelable {
  var name: String = ""
  var phoneNumber: String = ""
  var phone: String = ""
  var email: String = ""
  var password: String = ""
  var gender_id: Int = 1
  var date_of_birth: String = ""
  var firebase_token: String = ""
  var device_id: String = ""
}
