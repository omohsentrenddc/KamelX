package app.fawry.task.presentation.settings

import android.graphics.drawable.Drawable

data class SettingItem(
  var id: Any,
  var title: String? = "",
  var icon: Drawable?,
  var type: String = ""
)
