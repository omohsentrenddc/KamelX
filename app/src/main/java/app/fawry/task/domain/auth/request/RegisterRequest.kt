package app.fawry.task.domain.auth.request

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
class RegisterRequest : Parcelable {
  var first_name: String = ""
  var last_name: String = ""
  var password: String = ""
  var confirm_password: String = ""
  var phone: String = ""
  var firebase_token: String = ""
}
