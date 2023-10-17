package app.fawry.task.presentation.base.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.structure.base_mvvm.R

fun Context.navigate(v: View, schema: String, uriPath: String, appendPath : ArrayList<String> = arrayListOf(), withAnimation:Boolean = true,popTo: Int = -1){

  val options = NavOptions.Builder()
    .setEnterAnim(R.anim.anim_slide_in_right)
    .setExitAnim(R.anim.anim_slide_out_left)
    .setPopEnterAnim(R.anim.anim_slide_in_left)
    .setPopExitAnim(R.anim.anim_slide_out_right)


  if(popTo != -1){
    options.setPopUpTo(popTo,true,false)
  }

  val uri =  Uri.Builder()
    .scheme(schema)
    .authority(uriPath)


  appendPath.forEach {
    uri.appendPath(it)
  }
//  val uri = Uri.Builder()
//    .scheme(schema)
//    .authority(uriPath)
//    .build()
  val request = NavDeepLinkRequest.Builder
    .fromUri(uri.build())
    .build()

//  navigate(v,request, options)
  when(withAnimation){
    true -> v.findNavController().navigate(request,options.build())
    else -> v.findNavController().navigate(request)
  }
}

fun AppCompatActivity.navigate(nav: NavController, schema: String, uriPath: String, appendPath : ArrayList<String> = arrayListOf(), withAnimation:Boolean = true,popTo: Int = -1){

  val options = NavOptions.Builder()
    .setEnterAnim(R.anim.anim_slide_in_right)
    .setExitAnim(R.anim.anim_slide_out_left)
    .setPopEnterAnim(R.anim.anim_slide_in_left)
    .setPopExitAnim(R.anim.anim_slide_out_right)


  if(popTo != -1){
    options.setPopUpTo(popTo,true,false)
  }

  val uri =  Uri.Builder()
    .scheme(schema)
    .authority(uriPath)


  appendPath.forEach {
    uri.appendPath(it)
  }
//  val uri = Uri.Builder()
//    .scheme(schema)
//    .authority(uriPath)
//    .build()
  val request = NavDeepLinkRequest.Builder
    .fromUri(uri.build())
    .build()

//  navigate(v,request, options)
  when(withAnimation){
    true -> nav.navigate(request,options.build())
    else -> nav.navigate(request)
  }
}

fun Fragment.startActivityWithFinishPrev(activity: Class<*>){
  val intent = Intent(requireContext(), activity.javaClass)
  requireActivity().finishAffinity()
  startActivity(intent)
}