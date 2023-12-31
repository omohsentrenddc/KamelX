package app.fawry.task.presentation.base.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.KeyCharacterMap.load
import android.view.View
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.constraintlayout.widget.Guideline
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.snackbar.Snackbar
import com.structure.base_mvvm.R
import java.io.File
import kotlin.math.roundToInt

fun View.show() {
  if (visibility == View.VISIBLE) return

  visibility = View.VISIBLE
  if (this is Group) {
    this.requestLayout()
  }
}

fun View.hide() {
  if (visibility == View.GONE) return

  visibility = View.GONE
  if (this is Group) {
    this.requestLayout()
  }
}

fun View.showSnackBar(
  message: String,
  retryActionName: String? = null,
  action: (() -> Unit)? = null
) {
  val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)

  action?.let {
    snackBar.setAction(retryActionName) {
      it()
    }
  }

  snackBar.show()
}


@BindingAdapter("app:loadImageCache")
fun ImageView.loadImageCache(imageUrl: String?) {
  load(imageUrl){
    placeholder(R.drawable.loading)
    transformations(RoundedCornersTransformation(80f))
    error(R.drawable.bg_no_image)
  }
}

@BindingAdapter("load_drawable")
fun loadDrawable(imageView: ImageView, drawable: Drawable?) {
  imageView.setImageDrawable(drawable)
}

@BindingAdapter("layout_constraintGuide_begin")
fun setLayoutConstraintGuideBegin(guideline: Guideline, percent: Float) {
  val params: (ConstraintLayout.LayoutParams) =
    guideline.layoutParams as ConstraintLayout.LayoutParams;
  params.guidePercent = percent;
  guideline.layoutParams = params
}

private  val TAG = "ViewExtensions"
@BindingAdapter(
  value = ["app:loadImage", "app:progressBar", "app:placeHolder"],
  requireAll = false
)
fun ImageView.loadImage(imageUrl: String?, progressBar: ProgressBar?, defaultImage: Any?) {
  Log.d(TAG, "loadImage: HEREE $imageUrl")
  if (imageUrl != null && imageUrl.isNotEmpty()) {
    Log.d(TAG, "loadImage: DONER")
    if (URLUtil.isValidUrl(imageUrl)) {
      Log.d(TAG, "loadImage: $imageUrl")
      Glide
        .with(context)
        .load(imageUrl)
        .error(R.drawable.bg_no_image)
        .listener(object : RequestListener<Drawable>{
          override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
          ): Boolean {
            progressBar?.hide()
            return false
          }

          override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
          ): Boolean {
            progressBar?.hide()
            return false
          }

        })
        .into(this);


    }else if(defaultImage != null) {
      Glide
        .with(context)
        .load(defaultImage)
        .error(R.drawable.bg_no_image)
        .into(this);
    }
  } else {
    progressBar?.hide()
    when (defaultImage) {
      null -> {
        setImageResource(R.drawable.bg_no_image)
      }
      is Int -> setImageResource(defaultImage)
      is Drawable -> setImageDrawable(defaultImage)
    }
  }
}

fun RecyclerView.setUpAdapter(
  itemsAdapter: RecyclerView.Adapter<*>?,
  spanCount: String,
  orientation: String
) {
  if (orientation == "1") initVerticalRV(
    this,
    this.context,
    spanCount.toInt()
  ) else initHorizontalRV(this, this.context, spanCount.toInt())
  this.adapter = itemsAdapter

}

@BindingAdapter("app:adapter", "app:span", "app:orientation")
fun getItemsV2Binding(
  recyclerView: RecyclerView,
  itemsAdapter: RecyclerView.Adapter<*>?,
  spanCount: String,
  orientation: String
) {
  if (orientation == "1") initVerticalRV(
    recyclerView,
    recyclerView.context,
    spanCount.toInt()
  ) else initHorizontalRV(recyclerView, recyclerView.context, spanCount.toInt())
  recyclerView.adapter = itemsAdapter
}

@BindingAdapter("rv_ln","adapter_percentage","orientation")
fun loadDrawable(recyclerView: RecyclerView, percentage: Int,adapter: RecyclerView.Adapter<*>?, orientation : Int?) {
  val l = object : LinearLayoutManager(recyclerView.context, if(orientation == 0) HORIZONTAL else VERTICAL, false) {
    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
      if (lp != null) {
        lp.width = (width.toFloat() * (percentage.toFloat() / 100f)).roundToInt()
      }

      return super.checkLayoutParams(lp)
    }
  }
  recyclerView.layoutManager = l
  recyclerView.adapter = adapter
}



@SuppressLint("WrongConstant")
fun initVerticalRV(recyclerView: RecyclerView, context: Context?, spanCount: Int) {
  recyclerView.setHasFixedSize(true)
  recyclerView.setItemViewCacheSize(30)
  recyclerView.layoutManager =
    GridLayoutManager(context, spanCount, LinearLayoutManager.VERTICAL, false)
}

@SuppressLint("WrongConstant")
fun initHorizontalRV(recyclerView: RecyclerView, context: Context?, spanCount: Int) {
  recyclerView.setHasFixedSize(true)
  recyclerView.setItemViewCacheSize(30)
  recyclerView.layoutManager =
    GridLayoutManager(context, spanCount, LinearLayoutManager.HORIZONTAL, false)
}