package com.trenddc.hashksa.task.presentation.base.extensions

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.trenddc.hashksa.BuildConfig
import com.trenddc.hashksa.R
import com.trenddc.hashksa.task.presentation.base.showError
import com.trenddc.hashksa.task.presentation.base.utils.hideLoadingDialog
import com.trenddc.hashksa.task.presentation.base.utils.showLoadingDialog
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import org.json.JSONObject
import java.util.*


//private const val TAG = "SocialExtensions"
fun Fragment.facebookLogin(
  callbackManager: CallbackManager,
  loginButton: LoginButton,
  loginResult: (res : JSONObject?) -> Unit
) {

  if (BuildConfig.DEBUG) {
    FacebookSdk.setIsDebugEnabled(true);
    FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
  }
  val dialog = showLoadingDialog(requireActivity(),getString(R.string.please_wait))
  LoginManager.getInstance().logOut()
  listOf("email","public_profile")
  loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
    override fun onSuccess(result: LoginResult) {
      hideLoadingDialog(dialog,requireActivity())
      val graphRequest = GraphRequest.newMeRequest(result.accessToken) { obj, response ->
        loginResult(obj)
      }
      val parameters = Bundle()
      parameters.putString("fields", "id,name,email")
      graphRequest.parameters = parameters
      graphRequest.executeAsync()

      val request = GraphRequest.newMeRequest(
        result.accessToken,
        object : GraphRequest.GraphJSONObjectCallback {
          override fun onCompleted(obj: JSONObject?, response: GraphResponse?) {
//            Log.d(TAG, "onCompleted: ${obj.toString()}")
//            Log.d(TAG, "onCompleted: ${obj?.getString("email")}")
//            Log.d(TAG, "onCompleted: ${obj?.getString("name")}")
//            Toast.makeText(requireContext(), "${obj?.getString("name")}", Toast.LENGTH_SHORT).show()
            loginResult(obj)
          }
        })
      request.parameters = parameters
      request.executeAsync()

//      val credit = FacebookAuthProvider.getCredential(result.accessToken.token)
//      Firebase.auth.signOut()
//      val auth = Firebase.auth
//      auth.signInWithCredential(credit).addOnCompleteListener {task ->
////          Log.d(TAG, "onSuccess: ${task.result}")
//          Log.d(TAG, "onSuccess: ${auth.currentUser?.uid}")
//        if(task.isSuccessful){
//          Log.d(TAG, "onSuccess: ${auth.currentUser?.email}")
//          Log.d(TAG, "onSuccess: ${auth.currentUser?.uid}")
//          Toast.makeText(requireContext(), ""+auth.currentUser?.email+","+auth.currentUser?.uid, Toast.LENGTH_SHORT).show()
//        }
//      }
    }

    override fun onError(error: FacebookException) {
      hideLoadingDialog(dialog,requireActivity())
      requireContext().showError(getString(R.string.something_went_wrong_please_try_again)+ " error: "+error.message)
    }

    override fun onCancel() {
      hideLoadingDialog(dialog,requireActivity())
      requireContext().showError(getString(R.string.something_went_wrong_please_try_again))
    }
  })
  loginButton.performClick()
}
//
//fun Fragment.twitterLogin(
//  loginResult: (res : AuthResult) -> Unit
//){
////  val callBackURL = "https://hashksa-c69b1.firebaseapp.com/__/auth/handler"
////
////
////  val mTwitterAuthConfig = TwitterAuthConfig(
////    getString(com.trenddc.hashksa.R.string.twitter_consumer_key),
////    getString(com.trenddc.hashksa.R.string.twitter_consumer_secret)
////  )
////  val twitterConfig = TwitterConfig.Builder(requireContext())
////    .twitterAuthConfig(mTwitterAuthConfig)
////    .build()
////  Twitter.initialize(twitterConfig)
////
////
//
//
//
////  Log.d(TAG, "twitterLogin:")
////  val dialog = showLoadingDialog(requireActivity(),getString(R.string.please_wait))
////  val provider: OAuthProvider.Builder = OAuthProvider.newBuilder("twitter.com")
////  provider.addCustomParameter("lang", "ar");
////
////  //here
////  FirebaseApp.initializeApp(requireContext())
////  val mAuth = FirebaseAuth.getInstance()
////  Firebase.auth
////
////  val firebaseUser = mAuth.getCurrentUser()
////
////  if (firebaseUser != null) {
////    Log.d(TAG, "twitterLogin: not null")
////  } else {
////    Log.d(TAG, "twitterLogin: null")
////  }
////
////
////  Firebase.auth.signOut()
////  val firebaseAuth = FirebaseAuth.getInstance()
////  val pendingResultTask = firebaseAuth.pendingAuthResult
////  if (pendingResultTask != null) {
////    Log.d(TAG, "twitterLogin: not null")
////    // There's something already here! Finish the sign-in for your user.
////    pendingResultTask
////      .addOnSuccessListener {
////        Log.d(TAG, "twitterLogin: ${it.user?.uid}")
////        Log.d(TAG, "twitterLogin: ${it.user?.email}")
////        Log.d(TAG, "twitterLogin: ${it.user?.displayName}")
////      }
////      .addOnFailureListener {
////        // Handle failure.
////        Log.d(TAG, "twitterLogin: ${it.message}")
////      }
////  } else {
////    Log.d(TAG, "twitterLogin: here null")
////    // There's no pending result so you need to start the sign-in flow.
////    // See below.
////  }
//
//
////  firebaseAuth
////    .startActivityForSignInWithProvider(requireActivity(), provider.build())
////    .addOnSuccessListener {
////      hideLoadingDialog(dialog,requireActivity())
////      loginResult(it)
////    }
////    .addOnFailureListener {
////      // Handle failure.
////      hideLoadingDialog(dialog,requireActivity())
////      requireContext().showError(getString(R.string.something_went_wrong_please_try_again))
////    }
//}

fun Fragment.twitterLoginFirebase(
  loginResult: (res : AuthResult) -> Unit
){
  val dialog = showLoadingDialog(requireActivity(),getString(com.trenddc.hashksa.R.string.please_wait))
  val provider: OAuthProvider.Builder = OAuthProvider.newBuilder("twitter.com")
  //provider.addCustomParameter("lang", "fr");
  val firebaseAuth = FirebaseAuth.getInstance()

  val pendingResultTask = firebaseAuth.pendingAuthResult
  if (pendingResultTask != null) {
    // There's something already here! Finish the sign-in for your user.
    pendingResultTask
      .addOnSuccessListener {
        hideLoadingDialog(dialog,requireActivity())
        loginResult(it)
      }
      .addOnFailureListener {
        // Handle failure.
        hideLoadingDialog(dialog,requireActivity())

      }
  } else {
    // There's no pending result so you need to start the sign-in flow.
    // See below.
    firebaseAuth
      .startActivityForSignInWithProvider(requireActivity(), provider.build())
      .addOnSuccessListener {
        hideLoadingDialog(dialog,requireActivity())
        loginResult(it)
      }
      .addOnFailureListener {
        // Handle failure.
        hideLoadingDialog(dialog,requireActivity())
        Toast.makeText(requireContext(), "error ${it.message}", Toast.LENGTH_SHORT).show()
      }

  }


//  firebaseAuth
//    .startActivityForSignInWithProvider(requireActivity(), provider.build())
//    .addOnSuccessListener {
//      hideLoadingDialog(dialog,requireActivity())
//      loginResult(it)
//    }
//    .addOnFailureListener {
//      // Handle failure.
//      hideLoadingDialog(dialog,requireActivity())
//      requireContext().showError(getString(R.string.something_went_wrong_please_try_again))
//    }
}
