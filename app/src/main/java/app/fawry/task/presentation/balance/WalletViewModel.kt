package app.fawry.task.presentation.balance

import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.ObservableInt
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.presentation.base.extensions.isLoginWithOpenAuth
import app.fawry.task.presentation.base.extensions.navigate
import app.fawry.task.presentation.settings.SettingItem
import app.fawry.task.presentation.settings.SettingsAdapter
import com.structure.base_mvvm.BR
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
  private val userLocalUseCase: UserLocalUseCase,
) : BaseViewModel() {
  private val TAG = "WalletViewModel"

  val walletBalance = ObservableInt(-1)
  init {
  }

  fun changeWallet(number : Int){
    walletBalance.set(number)
  }
}