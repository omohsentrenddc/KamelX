package app.fawry.task.presentation.auth.login

import android.view.View
import androidx.lifecycle.viewModelScope
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.domain.auth.entity.LoginResponse
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.use_case.AuthUseCase
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.presentation.base.extensions.navigate
import app.fawry.task.presentation.base.extensions.showError
import app.fawry.task.presentation.base.utils.Constants
import com.structure.base_mvvm.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val useCase: AuthUseCase,
  private val userLocalUseCase: UserLocalUseCase,
) : BaseViewModel() {

  private val TAG = "LoginViewModel"
  val request = LogInRequest()
  val response =
    MutableStateFlow<Resource<BaseResponse<LoginResponse>>>(Resource.Default)

  init {
    request.firebase_token = userLocalUseCase.getKeyFromLocal(Constants.FIREBASE_TOKEN)
//    requestSocial.firebase_token = userLocalUseCase.getKeyFromLocal(Constants.FIREBASE_TOKEN)

  }

  fun submit(v: View) {
    if (request.phone.isEmpty()) {
      v.context.showError("${v.context.getString(R.string.please_enter)} ${v.context.getString(R.string.phone)}")
      return
    }
    if (request.password.isEmpty()) {
      v.context.showError("${v.context.getString(R.string.please_enter)} ${v.context.getString(R.string.password)}")
      return
    }
    useCase.login(request).onEach {
      response.value = it
    }.launchIn(viewModelScope)
  }

  fun forgetPassword(v: View){
    v.context.navigate(v, "forgetPassword", "app.kamelx.register")
  }

  fun register(v: View) {
    v.context.navigate(v, "registerFragment", "app.kamelx.register")
  }
}