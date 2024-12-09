package com.am_apps.recorded_money.core.presentation.navigation

import com.am_apps.recorded_money.R

data class TopLevelRoute(val name: String, val screen: Screen, val iconId: Int)

val recordPageRoutes = listOf<TopLevelRoute>(
    TopLevelRoute("Tasks", Screen.Task, R.drawable.list),
    TopLevelRoute("Attachments", Screen.Attachment, R.drawable.attach)
)