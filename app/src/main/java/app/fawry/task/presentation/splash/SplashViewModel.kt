package app.fawry.task.presentation.splash

import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  var useCase: UserLocalUseCase,
) : BaseViewModel() {
  private val TAG = "SplashViewModel"
}