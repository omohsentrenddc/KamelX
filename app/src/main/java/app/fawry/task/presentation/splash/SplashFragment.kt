package app.fawry.task.presentation.splash

import androidx.fragment.app.viewModels
import app.fawry.task.presentation.auth.login.LoginViewModel
import app.fawry.task.presentation.base.BaseFragment
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentLoginBinding>(){
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
  }


}