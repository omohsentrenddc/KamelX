package app.fawry.task.presentation.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import app.fawry.task.core.language.LanguagesHelper
import app.fawry.task.core.language.MyContextWrapper

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {
  private var _binding: VB? = null
  open val binding get() = _binding!!

  override
  fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initViewBinding()
    setContentView(binding.root)

    if (savedInstanceState == null) {
      setUpBottomNavigation()
      setUpNavigationDrawer()
    }

    setUpViews()
  }

  override
  fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    setUpBottomNavigation()
    setUpNavigationDrawer()
  }

  private fun initViewBinding() {
    _binding = DataBindingUtil.setContentView(this, getLayoutId())
    binding.lifecycleOwner = this
    binding.executePendingBindings()
  }

  @LayoutRes
  abstract fun getLayoutId(): Int

  open fun setUpBottomNavigation() {}
  open fun setUpNavigationDrawer() {}

  open fun setUpViews() {}

  override fun attachBaseContext(newBase: Context?) {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1 && newBase != null) {
      super.attachBaseContext(MyContextWrapper.wrap(newBase, LanguagesHelper.getCurrentLanguage()))
    } else {
      super.attachBaseContext(newBase)
    }
  }

}