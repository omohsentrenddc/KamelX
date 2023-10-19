package app.fawry.task.presentation.match.all

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import app.fawry.task.domain.home.model.match.MatchModel
import app.fawry.task.domain.home.use_case.HomeUseCase
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseViewModel
import app.fawry.task.presentation.home.adapter.HomeMatchAdapter
import com.structure.base_mvvm.BR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ResultMatchesViewModel @Inject constructor(
  private val useCase: HomeUseCase,
  ) : BaseViewModel() {
  private val TAG = "AllMatchesViewModel"

  var matchesAdapter = ResultMatchAdapter()

  val response =
    MutableStateFlow<Resource<BaseResponse<ArrayList<MatchModel>>>>(Resource.Default)


  init {
    useCase.allMatches().onEach {
      response.value = it
    }.launchIn(viewModelScope)
  }

  fun submitList(list: ArrayList<MatchModel>) {
    matchesAdapter.update(list)
    notifyPropertyChanged(BR.settingsAdpater)
  }

}