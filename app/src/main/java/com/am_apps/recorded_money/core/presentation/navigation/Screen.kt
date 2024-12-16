package com.am_apps.recorded_money.core.presentation.navigation

sealed class Screen(val route:String) {
    data object HomePage : Screen("home_page")
    data object AttachmentPage : Screen("attachment_page")
    data object TaskPage : Screen("task_page")
    data object RecordPage : Screen("record_page")

    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}