package app.fawry.task.presentation.notification

import androidx.lifecycle.viewModelScope
import app.fawry.task.domain.home.use_case.HomeUseCase
import app.fawry.task.domain.notification.NotificationItem
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseViewModel
import com.structure.base_mvvm.BR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
  private val useCase: HomeUseCase,
  ) : BaseViewModel() {
  private val TAG = "NewsViewModel"

  var adapter = NotificationsAdapter()

  val response =
    MutableStateFlow<Resource<BaseResponse<ArrayList<NotificationItem>>>>(Resource.Default)


  init {
    useCase.notification().onEach {
      response.value = it
    }.launchIn(viewModelScope)
  }

  fun submitList(list: ArrayList<NotificationItem>) {
    adapter.update(list)
    notifyPropertyChanged(BR.settingsAdpater)
  }

}