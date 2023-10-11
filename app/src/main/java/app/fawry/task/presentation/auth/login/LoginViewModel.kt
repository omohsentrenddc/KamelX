package com.trenddc.hashksa.task.presentation.auth.login

import android.util.Log
import android.view.View
import androidx.fragment.app.findFragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.trenddc.hashksa.task.domain.auth.entity.LoginResponse
import com.trenddc.hashksa.task.domain.auth.request.LogInRequest
import com.trenddc.hashksa.task.domain.auth.use_case.AuthUseCase
import com.trenddc.hashksa.task.domain.auth.use_case.UserLocalUseCase
import com.trenddc.hashksa.task.domain.utils.BaseResponse
import com.trenddc.hashksa.task.domain.utils.Resource
import com.trenddc.hashksa.task.presentation.base.BaseViewModel
import com.trenddc.hashksa.task.presentation.base.navigate
import com.trenddc.hashksa.task.presentation.base.showError
import com.trenddc.hashksa.task.presentation.base.utils.Constants
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthResult
import com.trenddc.hashksa.R
import com.trenddc.hashksa.task.domain.auth.request.social.LogInSocialRequest
import com.trenddc.hashksa.task.domain.auth.request.social.SocialType
import com.trenddc.hashksa.task.presentation.auth.forgetPassword.ForgetPasswordDialog
import com.trenddc.hashksa.task.presentation.auth.loginDialog.LoginDialog
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val useCase: AuthUseCase,
  private val userLocalUseCase: UserLocalUseCase,
) : BaseViewModel() {

  private val TAG = "LoginViewModel"
  val request = LogInRequest()
  val requestSocial = LogInSocialRequest()

  val response =
    MutableStateFlow<Resource<BaseResponse<LoginResponse>>>(Resource.Default)

  init {
    request.firebase_token = userLocalUseCase.getKeyFromLocal(Constants.FIREBASE_TOKEN)
    requestSocial.firebase_token = userLocalUseCase.getKeyFromLocal(Constants.FIREBASE_TOKEN)

  }

  fun submit(v: View) {
    if (request.name.isEmpty()) {
      v.context.showError("${v.context.getString(R.string.please_enter)} ${v.context.getString(R.string.name)}")
      return
    }
    if (request.password.isEmpty()) {
      v.context.showError("${v.context.getString(R.string.please_enter)} ${v.context.getString(R.string.password)}")
      return
    }
    useCase.login(request).onEach {
      response.value = it
    }.launchIn(viewModelScope)
  }

  fun register(v: View) {
    v.context.navigate(v, "registerFragment", "app.hashksa.register")
  }


  val responseSocial =
    MutableStateFlow<Resource<BaseResponse<LoginResponse>>>(Resource.Default)


  //{"id":"120555220906530","email":"osamatrendhub@gmail.com","name":"Osama Trend"}
  fun facebookLogin(result: JSONObject) {
    requestSocial.provider_name = SocialType.facebook.value
    requestSocial.name = result.getString("name")
    if (result.has("email"))
      requestSocial.email = result.getString("email")
    requestSocial.provider_id = result.getString("id")
    callSocialApi()
  }

  fun googleLogin(result: GoogleSignInAccount) {
    requestSocial.provider_name = SocialType.google.value
    Log.d(TAG, "googleLogin: ${requestSocial.provider_name}")
    requestSocial.name = result.displayName.toString()
    requestSocial.email = result.email.toString()
    requestSocial.provider_id = result.id.toString()
    callSocialApi()
  }

  private fun callSocialApi() {
    useCase.loginSocial(requestSocial).onEach {
      response.value = it
    }.launchIn(viewModelScope)
  }


  fun back(v: View){
    v.findFragment<LoginDialog>().findNavController().navigateUp()
  }

}