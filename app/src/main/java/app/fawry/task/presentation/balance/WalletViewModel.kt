package app.fawry.task.presentation.balance

import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.ObservableInt
import androidx.lifecycle.viewModelScope
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.domain.home.use_case.HomeUseCase
import app.fawry.task.domain.pricing.PricingItem
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.presentation.base.extensions.isLoginWithOpenAuth
import app.fawry.task.presentation.base.extensions.navigate
import app.fawry.task.presentation.settings.SettingItem
import app.fawry.task.presentation.settings.SettingsAdapter
import com.structure.base_mvvm.BR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
  private val useCase: HomeUseCase,
) : BaseViewModel() {
  private val TAG = "WalletViewModel"
  val response = MutableStateFlow<Resource<BaseResponse<ArrayList<PricingItem>>>>(Resource.Default)

  val walletBalance = ObservableInt(-1)
  var positionSelected = ObservableInt(-1)
  var planSelected = ObservableInt(-1)
  @Bindable
  var pricingPlans = ArrayList<PricingItem>()


  init {
    pricingPlans.add(PricingItem())
    pricingPlans.add(PricingItem())
    pricingPlans.add(PricingItem())
  }

  fun getPricingPlan(){
    useCase.getPricingPlan().onEach{
      response.value = it
    }.launchIn(viewModelScope)
  }

  fun changeWallet(number : Int){
    walletBalance.set(number)
  }

  fun setData(data: ArrayList<PricingItem>) {
    pricingPlans[0] = data[0]
    pricingPlans[1] = data[1]
    pricingPlans[2] = data[2]
    notifyPropertyChanged(BR.pricingPlans)
  }

  fun planSelected(planSelected: Int){
    this.planSelected.set(planSelected)
  }

}