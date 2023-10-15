package app.fawry.task.presentation.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import app.fawry.task.presentation.activity.auth.AuthActivity
import app.fawry.task.presentation.base.BaseActivity
import app.fawry.task.presentation.base.HomeActivity
import app.fawry.task.presentation.base.extensions.openActivityAndClearStack
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
  val viewModel: SplashViewModel by viewModels()


  override
  fun getLayoutId() = R.layout.activity_splash

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding.viewmodel = viewModel
    lifecycleScope.launchWhenResumed {
      delay(1000)
      if(viewModel.useCase.isLoggin())
        openActivityAndClearStack(HomeActivity::class.java)
      else
        openActivityAndClearStack(AuthActivity::class.java)
    }
  }
}