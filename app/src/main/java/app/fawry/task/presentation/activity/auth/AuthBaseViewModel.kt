package app.fawry.task.presentation.activity.auth

import app.fawry.task.presentation.base.BaseViewModel
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthBaseViewModel @Inject constructor(
) : BaseViewModel() {
  private val TAG = "BaseViewModel"

  init {
  }

}