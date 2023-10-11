package com.trenddc.hashksa.task.presentation.base

import android.view.View

interface ISubmitAction {
  fun submit(type: Any,position: Int = 0,constant: String = "")
}