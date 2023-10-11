package app.fawry.task.presentation.auth.login

import android.app.Activity
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseFragment
import app.fawry.task.presentation.base.HomeActivity
import app.fawry.task.presentation.base.extensions.handleApiError
import app.fawry.task.presentation.base.extensions.openActivityAndClearStack
import app.fawry.task.presentation.movie.ui_state.MovieUIState
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentLoginBinding
import com.structure.base_mvvm.databinding.FragmentMovieBinding
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

    (requireActivity() as AuthActivity).twitterLogin.callback = object : Callback<TwitterSession?>(){
      override fun success(result: Result<TwitterSession?>?) {
        authBaseViewModel.twitterLogin(result)
      }

      override fun failure(exception: TwitterException?) {
        Log.d(TAG, "failure: ${exception?.message}")
        requireContext().showError(getString(R.string.something_went_wrong_please_try_again))
      }
    }
  }

  val activityResultGoogleSignIn = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
  ) {
    Log.d(TAG, "${it.resultCode}: ")
    if (it.resultCode == Activity.RESULT_OK) {
      val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
      try {
        authBaseViewModel.googleLogin(
          task.getResult(ApiException::class.java)
        )
      } catch (throwable: Throwable) {
        Log.d(TAG, ": ${throwable.message}")
        error(throwable)
      }
    }
  }

  fun gmail(){
    Log.d(TAG, "gmail: here")
    val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
      .requestId()
      .requestEmail()
      .requestProfile()
      .build()
    val googleClient = GoogleSignIn.getClient(requireContext(), options)
    activityResultGoogleSignIn.launch(googleClient.signInIntent)
  }

}