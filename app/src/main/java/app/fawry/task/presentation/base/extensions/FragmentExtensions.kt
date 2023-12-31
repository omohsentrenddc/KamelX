package app.fawry.task.presentation.base.extensions

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import app.fawry.task.core.MyApplication
import app.fawry.task.data.local.preferences.AppPreferences
import app.fawry.task.domain.utils.FailureStatus
import app.fawry.task.domain.utils.Resource.Failure
import app.fawry.task.presentation.activity.auth.AuthActivity
import app.fawry.task.presentation.auth.login.LoginFragment
import app.fawry.task.presentation.base.HomeActivity
import app.fawry.task.presentation.base.utils.hideSoftInput
import app.fawry.task.presentation.base.utils.showMessage
import app.fawry.task.presentation.base.utils.showNoApiErrorAlert
import com.structure.base_mvvm.R

//extention function for handling api error , network , status code request
fun Fragment.handleApiError(
  failure: Failure,
  retryAction: (() -> Unit)? = null,
  noDataAction: (() -> Unit)? = null,
  noInternetAction: (() -> Unit)? = null
) {
  when (failure.failureStatus) {
    FailureStatus.EMPTY -> {
      noDataAction?.invoke()
      failure.message?.let { showNoApiErrorAlert(requireActivity(), it) }
    }
    FailureStatus.NO_INTERNET -> {
      noInternetAction?.invoke()
        app.fawry.task.presentation.base.utils.showNoInternetAlert(requireActivity())
    }
    FailureStatus.INVALID_TOKEN -> {
      AppPreferences(MyApplication.instance).clearUser()
      openActivityAndClearStack(AuthActivity::class.java)
      requireActivity().onBackPressed()
    }
    FailureStatus.API_FAIL -> {
      noDataAction?.invoke()
      failure.message?.let { showNoApiErrorAlert(requireActivity(),it) }
    }
    else -> {
      noDataAction?.invoke()
      requireView().showSnackBar(
        failure.message ?: resources.getString(R.string.some_error),
        resources.getString(R.string.retry),
        retryAction
      )
    }
  }
}

fun Fragment.getMyDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(requireContext(), id)!!

fun <A : Activity> Fragment.openActivityAndClearStack(activity: Class<A>) {
  requireActivity().openActivityAndClearStack(activity)
}

fun Fragment.hideKeyboard() = hideSoftInput(requireActivity())
