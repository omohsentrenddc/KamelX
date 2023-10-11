package app.fawry.task.presentation.activity.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.viewModelScope
import app.fawry.task.domain.auth.use_case.AuthUseCase
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.presentation.base.BaseViewModel
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class AuthBaseViewModel @Inject constructor(
  private val useCase: AuthUseCase,
  private val userLocalUseCase: UserLocalUseCase,
) : BaseViewModel() {
  private val TAG = "BaseViewModel"

  init {
  }

}