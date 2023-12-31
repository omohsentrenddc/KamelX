package app.fawry.task.presentation.match.all

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseFragment
import app.fawry.task.presentation.base.ISubmitAction
import app.fawry.task.presentation.base.extensions.handleApiError
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentResultMatchesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultMatchesFragment : BaseFragment<FragmentResultMatchesBinding>() , ISubmitAction {
  private  val TAG = "SettingsFragment"

  val viewModel: ResultMatchesViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_result_matches

  override
  fun setBindingVariables() {
    binding.viewmodel = viewModel
    viewModel.matchesAdapter.submitAction = this
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
    val settingItem = type as NewsModel
    when(settingItem.id){
    }
  }

  override fun onResume() {
    super.onResume()
  }

}