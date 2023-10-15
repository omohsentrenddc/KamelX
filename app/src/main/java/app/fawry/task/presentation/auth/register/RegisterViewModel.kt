package app.fawry.task.presentation.auth.register

import android.view.View
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.domain.auth.entity.LoginResponse
import app.fawry.task.domain.auth.request.LogInRequest
import app.fawry.task.domain.auth.request.RegisterRequest
import app.fawry.task.domain.auth.use_case.AuthUseCase
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.presentation.base.extensions.navigate
import app.fawry.task.presentation.base.extensions.showError
import app.fawry.task.presentation.base.utils.Constants
import com.structure.base_mvvm.BR
import com.structure.base_mvvm.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
  private val useCase: AuthUseCase,
  private val userLocalUseCase: UserLocalUseCase,
) : BaseViewModel() {

  private val TAG = "RegisterViewModel"
  val request = RegisterRequest()
  val response =
    MutableStateFlow<Resource<BaseResponse<LoginResponse>>>(Resource.Default)

  init {
    request.firebase_token = userLocalUseCase.getKeyFromLocal(Constants.FIREBASE_TOKEN)
//    requestSocial.firebase_token = userLocalUseCase.getKeyFromLocal(Constants.FIREBASE_TOKEN)

  }

  @Bindable
  var checked = false

  fun acceptPrivacy(v: View) {
    checked = !checked
    notifyPropertyChanged(BR.checked)
  }

  fun privacy(v: View){

  }

  fun submit(v: View) {
    if (request.first_name.isEmpty()) {
      v.context.showError("${v.context.getString(R.string.please_enter)} ${v.context.getString(R.string.first_name)}")
      return
    }
    if (request.last_name.isEmpty()) {
      v.context.showError("${v.context.getString(R.string.please_enter)} ${v.context.getString(R.string.last_name)}")
      return
    }
    if (request.phone.isEmpty()) {
      v.context.showError("${v.context.getString(R.string.please_enter)} ${v.context.getString(R.string.phone)}")
      return
    }
    if (request.password.isEmpty()) {
      v.context.showError("${v.context.getString(R.string.please_enter)} ${v.context.getString(R.string.password)}")
      return
    }
    if (request.confirm_password.isEmpty()) {
      v.context.showError("${v.context.getString(R.string.please_enter)} ${v.context.getString(R.string.confirm_password)}")
      return
    }
    if (request.password != request.confirm_password) {
      v.context.showError(v.context.getString(R.string.both_password_must_be_both))
      return
    }
    if (!checked) {
      v.context.showError(v.context.getString(R.string.please_accept_privacy_policy))
      return
    }
    useCase.register(request).onEach {
      response.value = it
    }.launchIn(viewModelScope)
  }
}