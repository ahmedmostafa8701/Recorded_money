package com.am_apps.recorded_money.core.presentation.navigation

sealed class Screen(val route:String) {
    data object HomePage : Screen("home_page")
    data object Attachment : Screen("attachment_page")

    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}