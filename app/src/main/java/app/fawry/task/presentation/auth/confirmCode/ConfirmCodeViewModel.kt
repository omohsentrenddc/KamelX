package app.fawry.task.presentation.auth.confirmCode

import android.os.CountDownTimer
import android.util.Log
import android.view.View
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
class ConfirmCodeViewModel @Inject constructor(
  private val useCase: AuthUseCase,
) : BaseViewModel() {
  var code: String = ""
  private val TAG = "ConfirmCodeViewModel"
  val request = ForgetPasswordRequest()
  var timerText = "60:00"
  var resend = false
  val response = MutableStateFlow<Resource<BaseResponse<ForgetPasswordResponse>>>(Resource.Default)


  init {
    request.code = ""
    startTimer()
  }

  fun back(v: View){
    v.findFragment<ConfirmCodeDialog>().findNavController().navigateUp()
  }

  fun submit(v: View) {
    if (request.code.isNullOrEmpty()) {
      v.context.showError(v.context.getString(R.string.enter_code))
      return
    }
    if(code != request.code){
      v.context.showError(v.context.getString(R.string.confirm_code_not_correct))
      return
    }
    useCase.confirmCode(request)
      .onEach {
        responseDefault.value = it
      }.launchIn(viewModelScope)
  }

  lateinit var countDownTimer: CountDownTimer
  fun resend(v: View) {
    Log.d(TAG, "resend: ")
    resend = false
    resendCode()
    countDownTimer.start()
    notifyChange()
  }

  fun resendCode(){
    val requestForgetPassword = ForgetPasswordRequest()
    requestForgetPassword.phone = request.phone
    requestForgetPassword.email = request.email
    useCase.forgetPassword(requestForgetPassword)
      .onEach {
        response.value = it
      }.launchIn(viewModelScope)
  }

  private fun startTimer() {
    Log.d(TAG, "startTimer: ")
    countDownTimer = object : CountDownTimer(60000, 1000) {
      override fun onTick(millisUntilFinished: Long) {
        timerText = when {
          (millisUntilFinished / 1000) < 10 -> "0" + (millisUntilFinished / 1000)
          else -> (millisUntilFinished / 1000)
        }.toString().plus(" : 00")
        notifyChange()
        Log.d(TAG, "onTick: $timerText")
      }

      override fun onFinish() {
        resend = true
        Log.d(TAG, "onFinish: resend")
        notifyChange()
      }
    }.start()
  }

}