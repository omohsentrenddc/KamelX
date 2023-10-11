package com.trenddc.hashksa.task.presentation.base

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.trenddc.hashksa.R
import com.trenddc.hashksa.task.core.MyApplication
import es.dmoral.toasty.Toasty

fun Context.showError( message: String) {
  try{
    Toasty.error(this, message, Toast.LENGTH_SHORT, true).show();
  }catch (e: Exception){
    Toasty.error(MyApplication.instance, message, Toast.LENGTH_SHORT, true).show();
  }
}

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

private fun removeHtml(html: String): String {
  var html = html
  html = html.replace("<(.*?)\\>".toRegex(), " ")
  html = html.replace("<(.*?)\\\n".toRegex(), " ")
  html = html.replaceFirst("(.*?)\\>".toRegex(), " ")
  html = html.replace("&nbsp;".toRegex(), " ")
  html = html.replace("&amp;".toRegex(), " ")
  return html
}

fun Context.share(message: String) {
  val intent = Intent(Intent.ACTION_SEND)
  intent.type = "text/plain"
  intent.putExtra(
    Intent.EXTRA_SUBJECT,
    getString(R.string.app_name)
  )
  intent.putExtra(
    Intent.EXTRA_TEXT,
    removeHtml(message)
  )
  /*Fire!*/
  startActivity(Intent.createChooser(intent, getString(R.string.share)))

}


fun Context.showInfo( message: String) {
  Toasty.info(this, message, Toast.LENGTH_SHORT, true).show();
}
