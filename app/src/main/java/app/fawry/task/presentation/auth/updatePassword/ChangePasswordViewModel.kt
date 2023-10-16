package app.fawry.task.presentation.auth.updatePassword

import android.view.View
import androidx.fragment.app.findFragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import app.fawry.task.domain.auth.request.UpdatePasswordRequest
import app.fawry.task.domain.home.use_case.HomeUseCase
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.presentation.base.extensions.showError
import com.structure.base_mvvm.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
  private val useCase: HomeUseCase,
  private val userLocalUseCase: UserLocalUseCase,
) : BaseViewModel() {
  private val TAG = "UpdatePasswordViewModel"
  val request = UpdatePasswordRequest()
  val userLogin =  userLocalUseCase.isLoggin()
  init {
  }
  fun back(v: View){
    v.findFragment<ChangePasswordDialog>().findNavController().navigateUp()
  }

  fun submit(v: View){
    if(request.password.isEmpty()) {
      v.context.showError(v.context.getString(R.string.enter_code) + " " +v.context.getString(R.string.new_password))
      return
    }
    if(request.password_confirmation.isEmpty()) {
      v.context.showError(v.context.getString(R.string.enter_code) + " " +v.context.getString(R.string.confirm_password))
      return
    }
    useCase.updatePassword(request)
      .onEach {
        responseDefault.value = it
      }.launchIn(viewModelScope)
  }
}