package app.fawry.task.presentation.base

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import app.fawry.task.presentation.base.extensions.hide
import app.fawry.task.presentation.base.extensions.navigate
import app.fawry.task.presentation.base.extensions.show
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var nav: NavController

  override
  fun getLayoutId() = R.layout.activity_home

  override
  fun setUpBottomNavigation() {
    setUpBottomNavigationWithGraphs()
  }

  private  val TAG = "HomeActivity"

  private fun setUpBottomNavigationWithGraphs() {
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.fragment_host_container) as NavHostFragment
    nav = navHostFragment.findNavController()

    appBarConfiguration = AppBarConfiguration(
      setOf(
//        R.id.homeFragment,
//        R.id.settingsFragment,
      ),
    )
    binding.toolbar.setTitleTextAppearance(this, R.style.RobotoBoldTextAppearance);
    setSupportActionBar(binding.toolbar)
    binding.toolbar.navigationIcon = null

    NavigationUI.setupActionBarWithNavController(this, nav)
    binding.toolbar.setupWithNavController(nav, appBarConfiguration)
    binding.bottomNavigation.setupWithNavController(nav)
    nav.addOnDestinationChangedListener { controller, destination, arguments ->
//      if (destination.id == R.id.profileFragment && !viewModel.userLocalUseCase.isLoggin()) {
//        startActivity(Intent(this, AuthActivity::class.java))
//        nav.navigateUp()
//      }
      when(destination.id){
        R.id.homeFragment -> binding.toolbar.hide()
        R.id.settingsFragment,R.id.newsFragment ->{
          binding.tvHomeTitle.text = destination.label
          binding.toolbar.show()
        }
        else -> binding.toolbar.show()
      }
      binding.toolbar.navigationIcon = null
//      when (destination.id) {
//        R.id.settingsFragment -> binding.toolbar.hide()
//        else -> binding.toolbar.show()
//      }
    }

    initClicks()

  }

  private fun initClicks() {
    binding.icMenu.setOnClickListener {
      navigate(nav, "more", "app.kamelx.more")
    }
    binding.icNotification.setOnClickListener {
      navigate(nav, "notifications", "app.kamelx.notifications")
    }
  }

  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }


}