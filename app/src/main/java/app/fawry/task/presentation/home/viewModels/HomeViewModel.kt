package app.fawry.task.presentation.home.viewModels

import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import app.fawry.task.core.MyApplication
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.domain.category.entity.Category
import app.fawry.task.domain.home.model.HomeResponse
import app.fawry.task.domain.home.use_case.HomeUseCase
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.presentation.base.extensions.navigate
import app.fawry.task.presentation.home.adapter.CategoriesAdapter
import app.fawry.task.presentation.home.adapter.HomeMatchAdapter
import com.structure.base_mvvm.BR
import com.structure.base_mvvm.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  var homeUseCase: HomeUseCase,
  var userLocalUseCase: UserLocalUseCase,
) : BaseViewModel() {
  private val TAG = "HomeViewModel"

  /** collect categories api for listening in fragment **/
  val homeResponse = MutableStateFlow<Resource<BaseResponse<HomeResponse>>>(Resource.Default)

  val adapter = HomeMatchAdapter()

//  @Bindable
  val homeModel = ObservableField<HomeResponse>()

  init {
//    getHome()
  }


  fun more(v: View){
    v.context.navigate(v, "more", "app.kamelx.more")
  }

  /** Get Categories Api using useCase invoke function **/
  fun getHome() {
    homeUseCase.home().onEach {
      homeResponse.value = it
    }.launchIn(viewModelScope)
  }

  fun name() : String{

    if(userLocalUseCase.isLoggin()){
      return "${MyApplication.instance.getString(R.string.welcome)} ${userLocalUseCase.invoke().fullName}"
    }
    return "${MyApplication.instance.getString(R.string.welcome)} ${MyApplication.instance.getString(R.string.visitor)}"
  }

  /** Get Categories Api using useCase invoke function **/
  fun moreNews(v: View) {

  }

  fun newsDetails(v: View) {

  }

  /**update data in category adapter**/
  fun setData(categories: List<Category>) {
//    adapter.update(categories)
//    notifyPropertyChanged(BR.adapter)
  }

  fun setData(response: HomeResponse) {
    Log.d(TAG, "setData: Starting")
    homeModel.set(response)
    adapter.update(response.matches)
//    notifyChange()
//    notifyPropertyChanged(BR.homeModel)
  }


}