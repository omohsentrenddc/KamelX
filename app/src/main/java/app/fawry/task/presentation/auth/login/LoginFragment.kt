package app.fawry.task.presentation.auth.login

import android.app.Activity
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.activity.auth.AuthActivity
import app.fawry.task.presentation.base.BaseFragment
import app.fawry.task.presentation.base.HomeActivity
import app.fawry.task.presentation.base.extensions.handleApiError
import app.fawry.task.presentation.base.extensions.openActivityAndClearStack
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(){
  val viewModel: LoginViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_login

  override
  fun setBindingVariables() {
    binding.viewmodel = viewModel
  }


  private val TAG = "LoginFragment"
  override fun setupObservers() {
    super.setupObservers()
    lifecycleScope.launchWhenResumed {
      viewModel.response.collect {
        handleLoading(it)
        Log.d(TAG, "setupObservers: worked $it")
        when (it) {
          is Resource.Loading -> handleLoading(it)
          is Resource.Success -> {
            openActivityAndClearStack(HomeActivity::class.java)
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
}