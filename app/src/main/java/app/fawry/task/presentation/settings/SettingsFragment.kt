package app.fawry.task.presentation.settings

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import app.fawry.task.presentation.base.BaseFragment
import app.fawry.task.presentation.base.HomeActivity
import app.fawry.task.presentation.base.ISubmitAction
import app.fawry.task.presentation.base.extensions.getMyDrawable
import app.fawry.task.presentation.base.extensions.openActivityAndClearStack
import app.fawry.task.presentation.base.utils.Constants
import app.fawry.task.presentation.settings.viewmodel.SettingsViewModel
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() , ISubmitAction {
  private  val TAG = "SettingsFragment"

  val viewModel: SettingsViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_settings

  override
  fun setBindingVariables() {
    binding.viewmodel = viewModel
    viewModel.settingsAdpater.submitAction = this
    viewModel.helpAdpater.submitAction = this
  }

  override fun setupObservers() {
    super.setupObservers()
    val list = ArrayList<SettingItem>()
    list.add(SettingItem(Constants.PROFILE,getString(R.string.profile),getMyDrawable(R.drawable.ic_more_profile)))
    list.add(SettingItem(Constants.EVENTS,getString(R.string.events),getMyDrawable(R.drawable.ic_more_events)))
    list.add(SettingItem(Constants.MAP_EVENTS,getString(R.string.map_events),getMyDrawable(R.drawable.ic_more_location)))
    list.add(SettingItem(Constants.JUDGEMENT,getString(R.string.judgement),getMyDrawable(R.drawable.ic_more_judgement_comity)))
    list.add(SettingItem(Constants.GIFTS,getString(R.string.gifts),getMyDrawable(R.drawable.ic_more_gifts)))
    list.add(SettingItem(Constants.AUCTION,getString(R.string.auction_camales),getMyDrawable(R.drawable.ic_more_camel_auction)))
    viewModel.submitListSettings(list)

    val listHelp = ArrayList<SettingItem>()
    listHelp.add(SettingItem(Constants.CONTACT_US,getString(R.string.contact_us),getMyDrawable(R.drawable.ic_more_contact_us)))
    listHelp.add(SettingItem(Constants.COMMON_QUESTIONS,getString(R.string.common_questions),getMyDrawable(R.drawable.ic_more_common_questions)))
    viewModel.submitListHelp(listHelp)
  }

  override fun submit(type: Any, position: Int, constant: String) {
    val settingItem = type as SettingItem
    when(settingItem.id){
//      Constants.PROFILE -> {
//        if (requireContext().isLoginWithOpenAuth()) {
//          requireContext().navigate(requireView(),"profile","com.trenddc.hashksa.profile.update")
//        }
//      }
//      Constants.CLASSIFICATION -> {
//        if (requireContext().isLoginWithOpenAuth()) {
////          val list = ArrayList<String>()
////          list.add(Constants.UPDATE)
////          requireContext().navigate(requireView(),"classifications","com.trenddc.hashksa.classifications",list)
//          findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToClassificationFragment2(Constants.UPDATE))
//        }
//      }
//      Constants.MY_EVENTS -> {
//        if (requireContext().isLoginWithOpenAuth()) {
//          requireContext().navigate(requireView(),"myevents","com.trenddc.hashksa.myevents")
//        }
//      }
//      Constants.CALL_US -> {
//        requireContext().navigate(requireView(),"callus","com.trenddc.hashksa.callus")
//      }
//      Constants.COMMON_QUESTIONS -> {
//        requireContext().navigate(requireView(),"commonquestions","com.trenddc.hashksa.common.questions")
//      }
//      Constants.PRIVACY -> {
//        val list = ArrayList<String>()
//        list.add(getString(R.string.privacy_policy))
//        list.add(Constants.PRIVACY)
//        requireContext().navigate(requireView(),"privacy","com.trenddc.hashksa.privacy",list)
//      }
//      Constants.TERMS -> {
//        val list = ArrayList<String>()
//        list.add(getString(R.string.terms_and_condition))
//        list.add(Constants.TERMS)
//        requireContext().navigate(requireView(),"privacy","com.trenddc.hashksa.privacy",list)
//      }
    }
  }

  override fun onResume() {
    super.onResume()
    viewModel.updateUser()
  }

}