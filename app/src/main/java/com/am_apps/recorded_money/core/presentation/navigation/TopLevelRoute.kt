package com.am_apps.recorded_money.core.presentation.navigation

import com.am_apps.recorded_money.R

data class TopLevelRoute<T : Any>(val name: String, val route: T, val iconId: Int)

val topLevelRoutes = listOf<TopLevelRoute<String>>(
    TopLevelRoute("Tasks", Screen.Task.route, R.drawable.list),
    TopLevelRoute("Attachments", Screen.Attachment.route, R.drawable.attach)
)