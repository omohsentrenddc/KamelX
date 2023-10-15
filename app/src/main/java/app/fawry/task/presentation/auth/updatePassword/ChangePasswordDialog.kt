package app.fawry.task.presentation.auth.updatePassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.extensions.handleApiError
import app.fawry.task.presentation.base.extensions.hide
import app.fawry.task.presentation.base.extensions.hideKeyboard
import app.fawry.task.presentation.base.extensions.show
import app.fawry.task.presentation.base.utils.showMessage
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.ChangePasswordDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordDialog : BottomSheetDialogFragment() {
  private val viewModel: ChangePasswordViewModel by viewModels()
  lateinit var binding: ChangePasswordDialogBinding
  val args : ChangePasswordDialogArgs by navArgs()
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DataBindingUtil.inflate(inflater, R.layout.change_password_dialog, container, false)
    binding.viewmodel = viewModel
    viewModel.request.phone = args.phone
    //Toast.makeText(requireContext(), viewModel.request.phone + ","+viewModel.request.email, Toast.LENGTH_SHORT).show()
    setupObserver()
    return binding.root
  }

  fun setupObserver(){
    lifecycleScope.launchWhenResumed {
      viewModel.responseDefault.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            binding.progress.visibility = View.VISIBLE
            binding.btnSubmit.hide()
          }
          is Resource.Success -> {
            showMessage(requireContext(),it.value.msg)
            binding.progress.visibility = View.GONE
            binding.btnSubmit.show()
            findNavController().navigateUp()
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