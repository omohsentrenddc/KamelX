package com.trenddc.hashksa.task.presentation.base.extensions

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.trenddc.hashksa.task.presentation.activity.auth.AuthActivity
import com.trenddc.hashksa.task.presentation.base.navigate
import com.trenddc.hashksa.task.presentation.base.utils.Constants
import com.trenddc.hashksa.task.presentation.survey.details.SurveyDetailsFragment

/**
 * @param percentage from 1 to 99
 */
fun Context.isLoginWithOpenAuth(): Boolean {
  val appPreferences: SharedPreferences =
    getSharedPreferences(Constants.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
  val isLogin = appPreferences.getInt("id", -1) != -1
  if (!isLogin) startActivity(Intent(this, AuthActivity::class.java))
  return isLogin
}

fun Context.isLoginWithOpenDialogAuth(fragment: Fragment): Boolean {
  val appPreferences: SharedPreferences =
    getSharedPreferences(Constants.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
  val isLogin = appPreferences.getInt("id", -1) != -1
  if (!isLogin) navigate(fragment.requireView(), "loginDialog", "com.trenddc.hashksa.loginDialog")
  return isLogin
}

fun Context.isLogin(): Boolean {
  val appPreferences: SharedPreferences =
    getSharedPreferences(Constants.APP_PREFERENCES_NAME, Context.MODE_PRIVATE)
  return appPreferences.getInt("id", -1) != -1
}


fun Context.checkSelfPermissionGranted(permission: String): Boolean {
  return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

