package app.fawry.task.presentation.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import app.fawry.task.domain.auth.use_case.UserLocalUseCase
import app.fawry.task.domain.news.NewsModel
import app.fawry.task.presentation.base.ISubmitAction
import app.fawry.task.presentation.settings.viewmodel.ItemSettingsViewModel
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ItemNewsBinding
import com.structure.base_mvvm.databinding.ItemSettingsBinding

class  NewsAdapter() : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
  lateinit var context: Context
  var clickEvent: MutableLiveData<NewsModel?> = MutableLiveData()
  lateinit var submitAction: ISubmitAction
  var list = ArrayList<NewsModel>()
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_news, parent, false)
    context = parent.context
    return ViewHolder(view)
  }

  private  val TAG = "MoreAdapter"
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = list[position]
    val itemViewModel = ItemNewsViewModel(data,position)
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

  fun update(list: ArrayList<NewsModel>){
    this.list.clear()
    this.list.addAll(list)
    notifyDataSetChanged()
  }

  fun update(position: Int, moreItem: NewsModel) {
    list[position] = moreItem
    notifyDataSetChanged()
  }

  inner class ViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    lateinit var itemLayoutBinding: ItemNewsBinding

    init {
      bind()
    }

    fun bind() {
      itemLayoutBinding = DataBindingUtil.bind(itemView)!!
    }

    fun unBind() {
      itemLayoutBinding.unbind()
    }

    fun setViewModel(itemViewModel: ItemNewsViewModel) {
      itemLayoutBinding.itemViewModels = itemViewModel
    }
  }

}