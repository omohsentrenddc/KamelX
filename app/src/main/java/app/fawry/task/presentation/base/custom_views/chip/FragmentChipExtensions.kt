package app.fawry.task.presentation.base.custom_views.chip

import android.util.Log
import androidx.fragment.app.Fragment
import app.fawry.task.presentation.base.extensions.getMyDrawable
import com.google.android.material.chip.ChipGroup
import com.structure.base_mvvm.R
import app.fawry.task.domain.home.entity.IdNameIcon

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

