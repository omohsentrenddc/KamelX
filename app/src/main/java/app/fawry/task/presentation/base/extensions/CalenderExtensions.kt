package com.trenddc.hashksa.task.presentation.base.extensions

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import com.trenddc.hashksa.R
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "CalenderExtensions"
fun Context.showCalenderDialog(blockNextDays:Boolean = false, chooseBirthDate:Boolean = true,blockPrevDays:Boolean = false ,dateFormat : String = "yyyy-MM-dd", result: (res: String) -> Unit) {
  val cal = Calendar.getInstance()
  var year = cal.get(Calendar.YEAR)
  if(chooseBirthDate)
    year -= 18;
  val month = cal.get(Calendar.MONTH)
  val day = cal.get(Calendar.DAY_OF_MONTH)

  Log.d(TAG, "showCalenderDialog: worked")
  val dpd = DatePickerDialog(
    this, R.style.datepicker,
    { view, year, monthOfYear, dayOfMonth ->
      Log.d(TAG, "showCalenderDialog: $year")
      Log.d(TAG, "showCalenderDialog: $monthOfYear")
      Log.d(TAG, "showCalenderDialog: $dayOfMonth")
      Log.d(TAG, "showCalenderDialog: $dateFormat")
      cal.set(Calendar.YEAR, year)
      cal.set(Calendar.MONTH, monthOfYear)
      cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
      val sdf = SimpleDateFormat(dateFormat, Locale.US)
      Log.d(TAG, "showCalenderDialog: ${sdf.format(cal.time)}")
      result(sdf.format(cal.time))
    },
    year,
    month,
    day
  )
  if(blockPrevDays) dpd.datePicker.minDate = System.currentTimeMillis()
  if(blockNextDays) dpd.datePicker.maxDate = Calendar.getInstance().time.time;
  dpd.show()
}

fun Context.showCalenderEventDialog(dateFormat : String = "yyyy-MM-dd", result: (res: String,cancel: Boolean) -> Unit) {
  val cal = Calendar.getInstance()
  var year = cal.get(Calendar.YEAR)
  val month = cal.get(Calendar.MONTH)
  val day = cal.get(Calendar.DAY_OF_MONTH)

  Log.d(TAG, "showCalenderDialog: worked")
  val dpd = DatePickerDialog(
    this, R.style.datepicker,
    { view, year, monthOfYear, dayOfMonth ->
      Log.d(TAG, "showCalenderDialog: $year")
      Log.d(TAG, "showCalenderDialog: $monthOfYear")
      Log.d(TAG, "showCalenderDialog: $dayOfMonth")
      Log.d(TAG, "showCalenderDialog: $dateFormat")
      cal.set(Calendar.YEAR, year)
      cal.set(Calendar.MONTH, monthOfYear)
      cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
      val sdf = SimpleDateFormat(dateFormat, Locale.US)
      Log.d(TAG, "showCalenderDialog: ${sdf.format(cal.time)}")
      result(sdf.format(cal.time),true)
    },
    year,
    month,
    day
  )
  dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, getString(R.string.reset), dpd);

  dpd.setOnCancelListener {
    result("",false)
  }
  dpd.show()
}


fun Context.showTimeDialog(result: (h:String,m:String) -> Unit){
  val mcurrentTime = Calendar.getInstance()
  val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
  val minute = mcurrentTime.get(Calendar.MINUTE)

  TimePickerDialog(this, R.style.datepicker, object : TimePickerDialog.OnTimeSetListener {
    override fun onTimeSet(p0: android.widget.TimePicker?, hourOfDay: Int, minute: Int) {
      val h:String = if(hourOfDay < 10) "0$hourOfDay" else "$hourOfDay"
      val m:String = if(minute < 10) "0$minute" else "$minute"
      result(h,m)
    }
  }, hour, minute, true).show()
}