package com.am_apps.recorded_money

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


fun setLanguage(context: Context, language: String) {
    // Change the language of the app
    val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("selected_language", language) // Store the language
        apply()
    }
    // Apply the locale immediately
    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language))
}

// this fun depends on two languages only
private const val FIRSTLANGUGE = "ar"
private const val SECONDLANGUAGE = "en"
fun switchLanguage(context: Context) {
    val currentLanguage = Locale.getDefault().language
    val newLanguage = if (currentLanguage == FIRSTLANGUGE) SECONDLANGUAGE else FIRSTLANGUGE
    setLanguage(context, newLanguage)
}

fun validateOnlyDoubles(text: String): Boolean {
    return text.matches(Regex("^-?\\d*(\\.\\d*)?\$")) && text.isNotEmpty()
}

fun datePicker(context: Context, onDateSelected: (String) -> Unit) {
    val c: Calendar = Calendar.getInstance()
    val year: Int = c.get(Calendar.YEAR)
    val month: Int = c.get(Calendar.MONTH)
    val day: Int = c.get(Calendar.DAY_OF_MONTH)
    val datePickerDialog = DatePickerDialog( context, 0,
        { _, y: Int, m: Int, d: Int ->
            val date = String.format(Locale.getDefault(), "%04d/%02d/%02d", y, m + 1, d)
            onDateSelected(date)
        }, year, month, day)
    datePickerDialog.show()
}
fun timePicker(context: Context, onTimeSelected: (String) -> Unit){
    val c: Calendar = Calendar.getInstance()
    val hour: Int = c.get(Calendar.HOUR_OF_DAY)
    val minute: Int = c.get(Calendar.MINUTE)
    val timePickerDialog = TimePickerDialog(context, { _, h: Int, m: Int ->
        val time = String.format(Locale.getDefault(), "%02d:%02d", h, m)
        onTimeSelected(time)
    }, hour, minute, true)
    timePickerDialog.show()
}

fun getCalendarFromStrings(date: String, time: String): Calendar{
    val calendar = Calendar.getInstance()
    val dateTimeFormat = SimpleDateFormat("yyyy/MM/dd HH:mm")
    val dateTime = "$date $time"
    dateTimeFormat.parse(dateTime)?.let { parsedDateTime ->
        calendar.time = parsedDateTime
    }
    return calendar
}