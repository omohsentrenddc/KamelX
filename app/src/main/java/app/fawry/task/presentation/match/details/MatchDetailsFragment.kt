package app.fawry.task.presentation.match.details

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseFragment
import app.fawry.task.presentation.base.extensions.handleApiError
import app.fawry.task.presentation.base.utils.Constants
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentMatchDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchDetailsFragment : BaseFragment<FragmentMatchDetailsBinding>() {
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

    viewModel.clickEventStr.observe(this){
      if(it == Constants.SUBMIT){
        findNavController().navigate(
          MatchDetailsFragmentDirections.actionMatchDetailsFragmentToVoteFragment(
            viewModel.position,
            viewModel.model.get()!!
          )
        )
      }
    }
  }

  override fun onResume() {
    super.onResume()
  }

}