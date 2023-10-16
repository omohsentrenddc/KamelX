package app.fawry.task.presentation.settings.viewmodel

import android.view.View
import androidx.databinding.Bindable
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.presentation.base.extensions.isLoginWithOpenAuth
import app.fawry.task.presentation.base.extensions.navigate
import app.fawry.task.presentation.settings.SettingItem
import app.fawry.task.presentation.settings.SettingsAdapter
import com.structure.base_mvvm.BR
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
  private val userLocalUseCase: UserLocalUseCase,
) : BaseViewModel() {
  private val TAG = "SettingsViewModel"

  @Bindable
  var settingsAdpater = SettingsAdapter(userLocalUseCase)


  @Bindable
  var helpAdpater = SettingsAdapter(userLocalUseCase)


  @Bindable
  var user = userLocalUseCase.invoke()

  @Bindable
  var login = userLocalUseCase.isLoggin()

  init {
  }

  fun updateUser() {
    val currentUser = userLocalUseCase.invoke()
    user = currentUser
    notifyPropertyChanged(fieldId = BR.user)
  }

  fun editProfile(v: View) {
    if (v.context.isLoginWithOpenAuth())
      v.context.navigate(v, "profile", "com.trenddc.hashksa.profile.update")
  }

  fun submitListSettings(list: ArrayList<SettingItem>) {
    settingsAdpater.update(list)
    notifyPropertyChanged(BR.settingsAdpater)
  }

  fun submitListHelp(list: ArrayList<SettingItem>) {
    helpAdpater.update(list)
    notifyPropertyChanged(BR.helpAdpater)
  }
}