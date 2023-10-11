package com.trenddc.hashksa.task.presentation.base

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.trenddc.hashksa.task.domain.utils.BaseResponse
import com.trenddc.hashksa.task.domain.utils.Resource
import com.trenddc.hashksa.task.presentation.base.extensions.handleApiError
import com.trenddc.hashksa.task.presentation.base.extensions.hideKeyboard
import com.trenddc.hashksa.task.presentation.base.utils.SingleLiveEvent
import com.trenddc.hashksa.task.presentation.base.utils.hideLoadingDialog
import com.trenddc.hashksa.task.presentation.base.utils.showLoadingDialog
import java.util.*

abstract class BaseTabsFragment: Fragment() {
  private var progressDialog: Dialog? = null
  override
  fun onResume() {
    super.onResume()
  }

  override
  fun onPause() {
    super.onPause()
  }

  override
  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  @LayoutRes
  abstract fun getLayoutId(): Int

  open fun registerListeners() {}

  open fun unRegisterListeners() {}

  open fun getFragmentArguments() {}


  open fun setUpViews() {}

  open fun observeAPICall() {}

  open fun setupObservers() {}

  private  val TAG = "BaseFragment"
  fun handleLoading(it: Resource<BaseResponse<*>>) {
    println("handleAction $it")
    Log.d(TAG, "handleLoading: ")
    when (it) {
      Resource.Loading -> {
        hideKeyboard()
        Log.d(TAG, "Loading: ")
      }
      is Resource.HideProgress -> {
        Log.d(TAG, "HideProgress: ")
      }
      is Resource.Success -> {
        Log.d(TAG, "Success: ")
      }
      is Resource.Failure -> {
        Log.d(TAG, "Failure: ")
      }
      else -> {}
    }
  }

  val currentLanguage: Locale
    get() = Locale.getDefault()


  fun showLoading() {
    hideLoading()
    progressDialog = showLoadingDialog(requireActivity(), null)
  }

  fun showLoading(hint: String?) {
    hideLoading()
    progressDialog = showLoadingDialog(requireActivity(), hint)
  }

  fun hideLoading() = hideLoadingDialog(progressDialog, requireActivity())


}