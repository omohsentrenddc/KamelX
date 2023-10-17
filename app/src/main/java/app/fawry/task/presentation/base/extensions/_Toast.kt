package app.fawry.task.presentation.base.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import app.fawry.task.core.MyApplication
import com.structure.base_mvvm.R
import es.dmoral.toasty.Toasty

fun Context.showError( message: String) {
  try{
    Toasty.error(this, message, Toast.LENGTH_SHORT, true).show();
  }catch (e: Exception){
    Toasty.error(MyApplication.instance, message, Toast.LENGTH_SHORT, true).show();
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
