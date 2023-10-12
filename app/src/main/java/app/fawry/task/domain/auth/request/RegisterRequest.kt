package app.fawry.task.domain.auth.request

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
class RegisterRequest : Parcelable {
  var firstName: String = ""
  var lastName: String = ""
  var phone: String = ""
  var password: String = ""
  var confirmPassword: String = ""
  var firebase_token: String = ""
}
