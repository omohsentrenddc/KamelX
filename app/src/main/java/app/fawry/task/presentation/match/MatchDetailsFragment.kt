package app.fawry.task.presentation.match

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseFragment
import app.fawry.task.presentation.base.ISubmitAction
import app.fawry.task.presentation.base.extensions.handleApiError
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentAllMatchesBinding
import com.structure.base_mvvm.databinding.FragmentMatchDetailsBinding
import com.structure.base_mvvm.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDetailsFragment : BaseFragment<FragmentMatchDetailsBinding>() , ISubmitAction {
  private  val TAG = "SettingsFragment"

  val viewModel: MatchDetailsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_match_details

  override
  fun setBindingVariables() {
    binding.viewmodel = viewModel
    if(arguments != null && arguments?.containsKey("id") == true){
      viewModel.matchDetails(requireArguments().getInt("id"))
    }
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
            viewModel.setData(it.value.data)
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