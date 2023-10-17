package app.fawry.task.presentation.home.ui

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.fawry.task.domain.utils.Resource
import app.fawry.task.presentation.base.BaseFragment
import app.fawry.task.presentation.base.extensions.handleApiError
import app.fawry.task.presentation.base.extensions.hideKeyboard
import app.fawry.task.presentation.base.extensions.setUpAdapter
import app.fawry.task.presentation.home.viewModels.HomeViewModel
import com.structure.base_mvvm.R
import com.structure.base_mvvm.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(){

  private  val TAG = "HomeFragment"

  private val viewModel: HomeViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_home

  override
  fun setBindingVariables() {
    Log.d(TAG, "setBindingVariables: ")
    binding.viewmodel = viewModel
    viewModel.getHome()
//    binding.rvCategories.setUpAdapter(viewModel.adapter,"1","2")
  }

  //add work-manager for cache movies
  private fun initWorkManager() {
  }

  override fun setupObservers() {
    super.setupObservers()
    //listen for categories api
    lifecycleScope.launchWhenResumed {
      viewModel.homeResponse.collect{
        handleLoading(it)
        when (it) {
          is Resource.Loading -> handleLoading(it)
          is Resource.Success -> {
            Log.d(TAG, "setupObservers: DONE ${it.value.data.news.image}")
            viewModel.setData(it.value.data)
          }
          is Resource.Failure -> {
            hideLoading()
            Log.d(TAG, "setupObservers: failure")
            handleApiError(it)
          }
          else ->{}
        }
      }
    }

//    //listen for movies api
//    lifecycleScope.launchWhenResumed {
//      viewModel.movieResponse
//        .collect {
//          when (it) {
//            Resource.Loading -> {
//              hideKeyboard()
//              showLoading()
//            }
//            is Resource.Success -> {
//              hideLoading()
//              //update movies in adapter
////              viewModel.updateMovies(it.value.movies)
//            }
//            is Resource.Failure -> {
//              hideLoading()
//              handleApiError(it)
//            }
//            else -> {}
//          }
//        }
//    }

  }

}