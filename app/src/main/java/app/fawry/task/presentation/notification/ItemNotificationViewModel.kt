package app.fawry.task.presentation.notification

import android.util.Log
import android.view.View
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.domain.notification.NotificationItem
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.presentation.settings.SettingItem


private const val TAG = "ItemMoreViewModel"

class ItemNotificationViewModel(val model: NotificationItem, val position: Int) :
  BaseViewModel() {
  fun submit(v: View) {
  }
}