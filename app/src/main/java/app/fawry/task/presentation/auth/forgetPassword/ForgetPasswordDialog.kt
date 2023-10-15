package app.fawry.task.presentation.auth.forgetPassword

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.extensions.handleApiError
import app.fawry.task.presentation.base.extensions.hide
import app.fawry.task.presentation.base.extensions.hideKeyboard
import app.fawry.task.presentation.base.extensions.show
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ForgetPasswordDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPasswordDialog : BottomSheetDialogFragment() {
  private val viewModel: ForgetPasswordViewModel by viewModels()
  lateinit var binding: ForgetPasswordDialogBinding
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.forget_password_dialog, container, false)
    binding.viewmodel = viewModel
    setupObserver()

    return binding.root
  }

  fun setupObserver(){
    lifecycleScope.launchWhenResumed {
      viewModel.response.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            binding.progress.visibility = View.VISIBLE
            binding.btnSubmit.hide()
          }
          is Resource.Success -> {
            binding.progress.visibility = View.GONE
            binding.btnSubmit.show()
//            Toast.makeText(requireContext(), it.value.data.code, Toast.LENGTH_SHORT).show()
            findNavController().navigate(ForgetPasswordDialogDirections.actionForgetPasswordDialogToConfirmCodeDialog(viewModel.request.phone , viewModel.request.email,it.value.data.code))
          }
          is Resource.Failure -> {
            binding.progress.visibility = View.GONE
            handleApiError(it)
            binding.btnSubmit.show()
          }
          else -> {}
        }
      }
    }
  }

  override fun getTheme(): Int {
    return R.style.CustomBottomSheetDialogTheme;
  }

  override fun onDestroy() {
    super.onDestroy()
  }
}