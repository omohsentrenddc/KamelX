package com.trenddc.hashksa.task.presentation.activity.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthResult
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.firebase.auth.AuthResult
import com.trenddc.hashksa.R
import com.trenddc.hashksa.task.domain.auth.entity.LoginResponse
import com.trenddc.hashksa.task.domain.auth.request.social.LogInSocialRequest
import com.trenddc.hashksa.task.domain.auth.request.social.SocialType
import com.trenddc.hashksa.task.domain.auth.use_case.AuthUseCase
import com.trenddc.hashksa.task.domain.auth.use_case.UserLocalUseCase
import com.trenddc.hashksa.task.domain.utils.BaseResponse
import com.trenddc.hashksa.task.domain.utils.Resource
import com.trenddc.hashksa.task.presentation.base.BaseViewModel
import com.trenddc.hashksa.task.presentation.base.navigate
import com.trenddc.hashksa.task.presentation.base.utils.Constants
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class AuthBaseViewModel @Inject constructor(
  private val useCase: AuthUseCase,
  private val userLocalUseCase: UserLocalUseCase,
) : BaseViewModel() {
  private val TAG = "BaseViewModel"
  val request = LogInSocialRequest()

  init {
    request.firebase_token = userLocalUseCase.getKeyFromLocal(Constants.FIREBASE_TOKEN)
  }
  val responseSocial =
    MutableStateFlow<Resource<BaseResponse<LoginResponse>>>(Resource.Default)

  fun forgetPassword(v: View){
    v.context.navigate(v,"forgetPasswordDialog","app.hashksa.forget.password")
  }

  fun privacy(v: View){
    val list = ArrayList<String>()
    list.add(v.context.getString(R.string.terms_and_condition))
    list.add(Constants.TERMS)
    v.context.navigate(v,"privacy","com.trenddc.hashksa.privacy",list)
  }

  fun loginFacebook(){
    clickEvent.value = Constants.FACEBOOK
  }

  fun loginGmail(){
    clickEvent.value = Constants.GMAIL

  }

  fun loginTwitter(){
    clickEvent.value = Constants.TWITTER
  }


  fun facebookLogin(result: JSONObject) {
    request.provider_name = SocialType.facebook.name
    request.name = result.getString("first_name") + " "+result.getString("last_name")
    request.email = result.getString("email")
    request.provider_id = result.getString("id")
    Log.d(TAG, "facebookLogin: ${request.name}")
    callSocialApi()
  }

  fun twitterLogin(result: Result<TwitterSession?>?) {
    Log.d(TAG, "twitterLogin: starting")
    request.provider_name = SocialType.twitter.name
    request.name = result?.data?.userName.toString()
    request.provider_id = result?.data?.id.toString()
    callSocialApi()
  }

  fun twitterLoginFirebase(result : AuthResult){
    request.provider_name = SocialType.twitter.name
    request.name = result.user?.displayName.toString()
    request.provider_id = result.user?.uid.toString()
    callSocialApi()
  }


  fun googleLogin(result: GoogleSignInAccount) {
    request.provider_name = SocialType.google.name
    request.name = result.displayName.toString()
    request.email = result.email.toString()
    request.provider_id = result.id.toString()
    callSocialApi()
  }

  private fun callSocialApi(){
    useCase.loginSocial(request).onEach {
      responseSocial.value = it
    }.launchIn(viewModelScope)
  }


}