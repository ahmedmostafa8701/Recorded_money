package com.am_apps.recorded_money

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
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
fun switchLanguage(context: Context){
    val currentLanguage = Locale.getDefault().language
    val newLanguage = if (currentLanguage == FIRSTLANGUGE) SECONDLANGUAGE else FIRSTLANGUGE
    setLanguage(context, newLanguage)
}

fun validateOnlyDoubles(text:String): Boolean{
    return text.matches(Regex("^-?\\d*(\\.\\d*)?\$")) && text.isNotEmpty()
}