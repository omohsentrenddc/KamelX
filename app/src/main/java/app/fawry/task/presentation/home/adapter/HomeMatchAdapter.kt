package app.fawry.task.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.domain.home.model.match.MatchModel
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.domain.notification.NotificationItem
import app.fawry.task.presentation.base.ISubmitAction
import app.fawry.task.presentation.home.viewModels.ItemMatchViewModel
import app.fawry.task.presentation.settings.viewmodel.ItemSettingsViewModel
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemMatchHomeBinding
import com.structure.base_mvvm.databinding.ItemNewsBinding
import com.structure.base_mvvm.databinding.ItemNotificationBinding
import com.structure.base_mvvm.databinding.ItemSettingsBinding

class  HomeMatchAdapter() : RecyclerView.Adapter<HomeMatchAdapter.ViewHolder>() {
  lateinit var context: Context
  var clickEvent: MutableLiveData<NotificationItem?> = MutableLiveData()
  lateinit var submitAction: ISubmitAction
  var list = ArrayList<MatchModel>()
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_match_home, parent, false)
    context = parent.context
    return ViewHolder(view)
  }

  private  val TAG = "MoreAdapter"
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = list[position]
    val itemViewModel = ItemMatchViewModel(data,position)
    if(this::submitAction.isInitialized)
      holder.itemLayoutBinding.iSubmitAction = submitAction
    holder.setViewModel(itemViewModel)

  }

  override fun getItemCount(): Int {
    return list.size
  }

  override fun onViewAttachedToWindow(holder: ViewHolder) {
    super.onViewAttachedToWindow(holder)
    holder.bind()
  }

  override fun onViewDetachedFromWindow(holder: ViewHolder) {
    super.onViewDetachedFromWindow(holder)
    holder.unBind()
  }

  fun update(list: ArrayList<MatchModel>){
    this.list.clear()
    this.list.addAll(list)
    notifyDataSetChanged()
  }

  fun update(position: Int, moreItem: MatchModel) {
    list[position] = moreItem
    notifyDataSetChanged()
  }

  inner class ViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    lateinit var itemLayoutBinding: ItemMatchHomeBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setViewModel(itemViewModel: ItemMatchViewModel) {
      itemLayoutBinding.itemViewModels = itemViewModel
    }
  }

}