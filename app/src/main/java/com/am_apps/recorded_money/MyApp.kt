package com.am_apps.recorded_money

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import dagger.hilt.android.HiltAndroidApp

//import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("selected_language", "en") ?: "en" // Default to English

        // Apply the saved locale
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language))
    }
}