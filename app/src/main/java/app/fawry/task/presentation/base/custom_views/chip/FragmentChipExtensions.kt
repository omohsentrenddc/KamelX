package com.trenddc.hashksa.task.presentation.base.custom_views.chip

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.provider.MediaStore
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.webkit.URLUtil
import androidx.annotation.DrawableRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.chip.ChipGroup
import com.trenddc.hashksa.R
import com.trenddc.hashksa.task.domain.home.entity.IdNameIcon
import com.trenddc.hashksa.task.domain.home.entity.slider.SliderModel
import com.trenddc.hashksa.task.domain.utils.FailureStatus
import com.trenddc.hashksa.task.domain.utils.Resource
import com.trenddc.hashksa.task.domain.utils.Resource.Failure
import com.trenddc.hashksa.task.presentation.base.extensions.getMyDrawable
import com.trenddc.hashksa.task.presentation.base.showError
import com.trenddc.hashksa.task.presentation.base.utils.hideSoftInput
import com.trenddc.hashksa.task.presentation.base.utils.showNoApiErrorAlert
import com.trenddc.hashksa.task.presentation.base.utils.showNoInternetAlert
//import kotlinx.android.synthetic.main.fragment_map.view.*


private const val TAG = "FragmentExtensions"

fun Fragment.tagsInit(
  chipGroup: ChipGroup,
  data: List<IdNameIcon>,
  showIconImage: Boolean = true,
  selected: List<IdNameIcon> = arrayListOf(),
  callBack: ((it: GlideChip) -> Unit),
) {
  data.forEach {
    val chips = GlideChip(requireContext())
    chips.setText(it.name)
    chips.tag = it.id
    if(showIconImage)
      chips.setIconUrl(it.icon,getMyDrawable(R.drawable.bg_no_image))
    chips.setUnSelected()
    chips.setOnClickListener {
      when(chips.select){
        true -> {
          chips.select = false
          chips.setUnSelected()
        }
        else -> {
          chips.select = true
          chips.setSelected()
        }
      }
      callBack(chips)
    }
    Log.d(TAG, "tagsInit: ${selected.size}")
    if(selected.isNotEmpty()){
      selected.forEach { selectItem ->
        Log.d(TAG, "tagsInit: ${it.id} , ${selectItem.id}")
        if(it.id == selectItem.id){
          chips.performClick()
        }
      }
    }
    chipGroup.addView(chips)
  }
}

