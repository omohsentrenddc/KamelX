package app.fawry.task.presentation.activity.auth

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import app.fawry.task.core.language.LanguagesHelper
import app.fawry.task.presentation.base.BaseActivity
import app.fawry.task.presentation.base.extensions.hide
import app.fawry.task.presentation.base.extensions.show
import com.structure.base_mvvm.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import com.structure.base_mvvm.R


@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {
  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var nav: NavController

  //  lateinit var callbackManager: CallbackManager
//  private lateinit var auth: FirebaseAuth

//  lateinit var twitterLogin: TwitterLoginButton

  override
  fun getLayoutId() = R.layout.activity_auth

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    auth = Firebase.auth
//    callbackManager = CallbackManager.Factory.create()

//    Twitter.initialize(this)
//    twitterLogin = TwitterLoginButton(this,null)

    Locale.setDefault(Locale(LanguagesHelper.getCurrentLanguage()));
  }


  private val TAG = "HomeActivity"

  override fun setUpBottomNavigation() {
    super.setUpBottomNavigation()

    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
    nav = navHostFragment.findNavController()
    appBarConfiguration = AppBarConfiguration(
      setOf(
      ),
    )
//    binding.toolbar.setTitleTextAppearance(this, R.style.toolbar_style)
    binding.toolbar.setTitleTextAppearance(this, R.style.RobotoBoldTextAppearance);
    binding.toolbar.title = getString(R.string.login)
    setSupportActionBar(binding.toolbar)

//    supportActionBar?.setDisplayHomeAsUpEnabled(true);
//    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow)
//    supportActionBar?.setDisplayShowHomeEnabled(true);
    binding.toolbar.setupWithNavController(nav, appBarConfiguration)
    nav.addOnDestinationChangedListener { controller, destination, arguments ->
      binding.toolbar.title = destination.label
      when(destination.id){
        R.id.loginFragment -> {
          binding.toolbar.navigationIcon = null
        }
        else -> {
          binding.toolbar.navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.ic_back_circle_white, null)
        }
      }
    }
  }
  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//    val menuToUse = R.menu.my_right_side_menu
//
//    val inflater: MenuInflater = menuInflater
//    inflater.inflate(menuToUse, menu)

    return super.onCreateOptionsMenu(menu)
  }

  override fun onStart() {
    super.onStart()
//    if (this::auth.isInitialized) {
//      val currentUser = auth.currentUser
//      updateUI(currentUser)
//    }
  }

//  private fun updateUI(currentUser: FirebaseUser?) {
//    Log.d(TAG, "updateUI: ${currentUser?.uid}")
//  }

}