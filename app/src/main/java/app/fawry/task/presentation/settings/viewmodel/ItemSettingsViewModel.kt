package app.fawry.task.presentation.settings.viewmodel

import android.util.Log
import android.view.View
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.presentation.settings.SettingItem


private const val TAG = "ItemMoreViewModel"

class ItemSettingsViewModel(val moreItem: SettingItem, val position: Int, val userLocalUseCase: UserLocalUseCase) :
  BaseViewModel() {
  fun submit(v: View) {
    Log.d(TAG, "submit: ${moreItem.id}")
    when (moreItem.id) {

    }
  }
}