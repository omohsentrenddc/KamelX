package app.fawry.task.presentation.notification

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.notification.NotificationItem
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseFragment
import app.fawry.task.presentation.base.ISubmitAction
import app.fawry.task.presentation.base.extensions.handleApiError
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentNewsBinding
import com.structure.base_mvvm.databinding.FragmentNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : BaseFragment<FragmentNotificationsBinding>() , ISubmitAction {
  private  val TAG = "SettingsFragment"

  val viewModel: NotificationViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_notifications

  override
  fun setBindingVariables() {
    binding.viewmodel = viewModel
    binding.recyclerViewSettings.adapter = viewModel.adapter
    viewModel.adapter.submitAction = this
  }

  override fun setupObservers() {
    super.setupObservers()
    lifecycleScope.launchWhenResumed {
      viewModel.response.collect{
        handleLoading(it)
        when (it) {
          is Resource.Loading -> handleLoading(it)
          is Resource.Success -> {
            viewModel.submitList(it.value.data)
          }
          is Resource.Failure -> {
            hideLoading()
            Log.d(TAG, "setupObservers: failure")
            handleApiError(it)
          }
          else ->{}
        }
      }
    }
  }

  override fun submit(type: Any, position: Int, constant: String) {
    val settingItem = type as NotificationItem
    when(settingItem.id){
    }
  }

  override fun onResume() {
    super.onResume()
  }

}