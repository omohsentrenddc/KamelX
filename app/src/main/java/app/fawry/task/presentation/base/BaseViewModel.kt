package app.fawry.task.presentation.base

import androidx.lifecycle.ViewModel
import app.fawry.task.presentation.base.utils.SingleLiveEvent
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import app.fawry.task.domain.utils.BaseResponse
import app.fawry.task.domain.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow

open class BaseViewModel : ViewModel(), Observable {
  private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

  var dataLoadingEvent: SingleLiveEvent<Int> = SingleLiveEvent()
  var clickEvent: SingleLiveEvent<Int> = SingleLiveEvent()
  var clickEventStr: SingleLiveEvent<String> = SingleLiveEvent()
  val responseDefault =
    MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)


  fun clickEvent(action: Int) {
    clickEvent.value = action
  }

  override fun addOnPropertyChangedCallback(
    callback: Observable.OnPropertyChangedCallback
  ) {
    callbacks.add(callback)
  }

  override fun removeOnPropertyChangedCallback(
    callback: Observable.OnPropertyChangedCallback
  ) {
    callbacks.remove(callback)
  }

  /**
   * Notifies observers that all properties of this instance have changed.
   */
  fun notifyChange() {
    callbacks.notifyCallbacks(this, 0, null)
  }

  /**
   * Notifies observers that a specific property has changed. The getter for the
   * property that changes should be marked with the @Bindable annotation to
   * generate a field in the BR class to be used as the fieldId parameter.
   *
   * @param fieldId The generated BR id for the Bindable field.
   */
  fun notifyPropertyChanged(fieldId: Int) {
    callbacks.notifyCallbacks(this, fieldId, null)
  }

//  fun getArgs(key: String, dataType: Objects, savedStateHandle: SavedStateHandle): Any? {
//    savedStateHandle.get<dataType>(key)?.let {
//      return it
//    }
//    return null
//  }
}