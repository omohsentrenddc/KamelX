package app.fawry.task.presentation.balance

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.fawry.task.presentation.base.BaseFragment
import app.fawry.task.presentation.base.HomeActivity
import app.fawry.task.presentation.base.ISubmitAction
import app.fawry.task.presentation.base.extensions.getMyDrawable
import app.fawry.task.presentation.base.extensions.openActivityAndClearStack
import app.fawry.task.presentation.base.utils.Constants
import app.fawry.task.presentation.settings.viewmodel.SettingsViewModel
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentSettingsBinding
import com.structure.base_mvvm.databinding.FragmentWalletBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalletFragment : BaseFragment<FragmentWalletBinding>() , ISubmitAction {
  private  val TAG = "WalletFragment"

  val viewModel: WalletViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_wallet

  override
  fun setBindingVariables() {
    binding.viewmodel = viewModel
  }

  override fun setupObservers() {
    super.setupObservers()
  }

  override fun submit(type: Any, position: Int, constant: String) {
  }

  override fun onResume() {
    super.onResume()
  }

}