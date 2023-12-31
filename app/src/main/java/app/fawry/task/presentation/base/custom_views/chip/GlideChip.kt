package app.fawry.task.presentation.base.custom_views.chip

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import app.fawry.task.core.MyApplication
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.chip.Chip
import com.structure.base_mvvm.R


class GlideChip(context: Context) : Chip(context) {

  var select:Boolean = false

  fun setText(name: String): GlideChip {
    this.text = name
    setTextAppearance(R.style.chipText)
    return this
  }
  fun setIconUrl(url: String?, errDrawable: Drawable?): GlideChip {
    Glide.with(this)
      .load(url)
      .listener(object : RequestListener<Drawable?> {
        override fun onLoadFailed(
          e: GlideException?,
          model: Any?,
          target: Target<Drawable?>?,
          isFirstResource: Boolean
        ): Boolean {
          chipIcon = errDrawable
          return true
        }

        override fun onResourceReady(
          resource: Drawable?,
          model: Any?,
          target: Target<Drawable?>?,
          dataSource: DataSource?,
          isFirstResource: Boolean
        ): Boolean {
          chipIcon = resource
          return true
        }

      }).preload()
    return this
  }

  fun setSelected(){
    setTextColor(MyApplication.instance.getColor(R.color.colorPrimary))
    chipBackgroundColor = getColorStateList(context,R.color.colorPrimaryDark)
    val states = arrayOf(
      intArrayOf(android.R.attr.state_enabled), // enabled
    )

    val colors = intArrayOf(
      MyApplication.instance.getColor(R.color.colorPrimary),
    )

    val myList = ColorStateList(states, colors)

    chipStrokeColor = myList
  }

  fun setUnSelected(){
    val states = arrayOf(
      intArrayOf(android.R.attr.state_enabled), // enabled
    )

    val colors = intArrayOf(
      MyApplication.instance.getColor(R.color.colorPrimaryDark),
    )

    val myList = ColorStateList(states, colors)


    setTextColor(MyApplication.instance.getColor(R.color.colorAccent))
    chipBackgroundColor = getColorStateList(context, R.color.white)
    background = ContextCompat.getDrawable(context,R.drawable.bg_no_image)
    backgroundDrawable = ContextCompat.getDrawable(context,R.drawable.bg_no_image)
    chipStrokeWidth = 3f
    chipStrokeColor = myList
  }

}