package app.fawry.task.presentation.auth.register

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
import com.google.android.material.textfield.TextInputLayout
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentLoginBinding
import com.structure.base_mvvm.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(){
  val viewModel: RegisterViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_register

  override
  fun setBindingVariables() {
    binding.viewmodel = viewModel

    //binding.tilPassword.isEnabled = false
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