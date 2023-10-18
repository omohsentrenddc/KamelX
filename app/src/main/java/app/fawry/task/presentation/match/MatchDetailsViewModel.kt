package app.fawry.task.presentation.match

import androidx.databinding.Bindable
import androidx.databinding.ObservableField
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
class MatchDetailsViewModel @Inject constructor(
  private val useCase: HomeUseCase,
  ) : BaseViewModel() {
  private val TAG = "AllMatchesViewModel"

  var matchesAdapter = HomeMatchAdapter()

  val response =
    MutableStateFlow<Resource<BaseResponse<MatchModel>>>(Resource.Default)

  var model = ObservableField<MatchModel>()
  init {
  }

  fun matchDetails(id: Int){
    useCase.matchDetails(id).onEach {
      response.value = it
    }.launchIn(viewModelScope)

  }

  fun setData(data: MatchModel) {
    this.model.set(data)
  }

}