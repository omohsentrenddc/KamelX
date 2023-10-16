package app.fawry.task.presentation.news

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import app.fawry.task.domain.home.use_case.HomeUseCase
import app.fawry.task.domain.news.NewsModel
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
class NewsViewModel @Inject constructor(
  private val useCase: HomeUseCase,
  ) : BaseViewModel() {
  private val TAG = "NewsViewModel"

  @Bindable
  var newsAdapter = NewsAdapter()

  val response =
    MutableStateFlow<Resource<BaseResponse<ArrayList<NewsModel>>>>(Resource.Default)


  init {
    useCase.news().onEach {
      response.value = it
    }.launchIn(viewModelScope)
  }

  fun submitList(list: ArrayList<NewsModel>) {
    newsAdapter.update(list)
    notifyPropertyChanged(BR.settingsAdpater)
  }

}