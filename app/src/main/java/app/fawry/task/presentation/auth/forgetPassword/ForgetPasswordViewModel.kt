package app.fawry.task.presentation.auth.forgetPassword

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.fragment.app.findFragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordRequest
import app.fawry.task.domain.auth.request.forgetPassword.ForgetPasswordResponse
import app.fawry.task.domain.auth.use_case.AuthUseCase
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.presentation.base.extensions.showError
import com.structure.base_mvvm.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
  private val useCase: AuthUseCase,
) : BaseViewModel() {
  private val TAG = "ForgetPasswordViewModel"
  val request = ForgetPasswordRequest()

  val response = MutableStateFlow<Resource<BaseResponse<ForgetPasswordResponse>>>(Resource.Default)

  var phone = ObservableBoolean(true)

  fun back(v: View){
    v.findFragment<ForgetPasswordDialog>().findNavController().navigateUp()
  }

  fun email(v: View){
    phone.set(!phone.get())
    if(phone.get()) request.email = null
    else request.phone = null
  }

  fun submit(v: View){
    if(phone.get() && request.phone?.isEmpty() == true) {
      v.context.showError("${v.context.getString(R.string.please_enter)} ${v.context.getString(R.string.phone)}")
      return
    }

    if(phone.get()) request.email = null else request.phone = null

    useCase.forgetPassword(request).onEach {
      response.value = it
    }.launchIn(viewModelScope)

  }

  fun submitEmail(v: View){

  }
}