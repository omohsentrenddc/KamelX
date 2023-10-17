package app.fawry.task.presentation.home.viewModels

import android.util.Log
import android.view.View
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.domain.home.model.match.MatchModel
import app.fawry.task.domain.notification.NotificationItem
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.presentation.settings.SettingItem


private const val TAG = "ItemMoreViewModel"

class ItemMatchViewModel(val model: MatchModel, val position: Int) :
  BaseViewModel() {

  fun vote(): Float{
    return 0.3f;
  }

  fun submit(v: View) {
  }
}