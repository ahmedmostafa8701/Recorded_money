package com.am_apps.recorded_money.core.presentation.navigation

import com.am_apps.recorded_money.MyApp
import com.am_apps.recorded_money.R

data class TopLevelRoute(val name: String, val screen: Screen, val iconId: Int)

val recordPageRoutes = listOf<TopLevelRoute>(
    TopLevelRoute(MyApp.instance.getString(R.string.tasks), Screen.TaskPage, R.drawable.list),
    TopLevelRoute(MyApp.instance.getString(R.string.attachments), Screen.AttachmentPage, R.drawable.attach)
)