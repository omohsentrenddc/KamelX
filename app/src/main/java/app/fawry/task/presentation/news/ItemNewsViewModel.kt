package app.fawry.task.presentation.news

import android.util.Log
import android.view.View
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.presentation.settings.SettingItem


private const val TAG = "ItemMoreViewModel"

class ItemNewsViewModel(val model: NewsModel, val position: Int) :
  BaseViewModel() {
  fun submit(v: View) {
    Log.d(TAG, "submit: ${model.id}")
    when (model.id) {

    }
  }
}