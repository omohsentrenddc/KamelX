package app.fawry.task.presentation.vote

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseFragment
import app.fawry.task.presentation.base.HomeActivity
import app.fawry.task.presentation.base.ISubmitAction
import app.fawry.task.presentation.base.extensions.getMyDrawable
import app.fawry.task.presentation.base.extensions.handleApiError
import app.fawry.task.presentation.base.extensions.openActivityAndClearStack
import app.fawry.task.presentation.base.utils.Constants
import app.fawry.task.presentation.settings.viewmodel.SettingsViewModel
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentSettingsBinding
import com.structure.base_mvvm.databinding.FragmentVoteBinding
import com.structure.base_mvvm.databinding.FragmentWalletBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class VoteFragment : BaseFragment<FragmentVoteBinding>() , ISubmitAction {
  private  val TAG = "WalletFragment"

  val args: VoteFragmentArgs by navArgs()

  val viewModel: VoteViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_vote

  override
  fun setBindingVariables() {
    binding.viewmodel = viewModel
    viewModel.getPricingPlan()
    viewModel.setDataArgs(args.position,args.matchModel)
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
  }

  override fun onResume() {
    super.onResume()
  }

}