package com.am_apps.recorded_money.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.am_apps.recorded_money.features.attachments.presentation.pages.AttachmentPage
import com.am_apps.recorded_money.features.attachments.presentation.view_model.AttachmentViewModel
import com.am_apps.recorded_money.features.home_page.presentation.pages.HomePage

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomePage.route) {
        composable(route = Screen.HomePage.route) { backStackEntry ->
            HomePage(navController, viewModel = hiltViewModel(backStackEntry))
        }
        composable(
            Screen.Attachment.route + "/{recordId}",
            arguments = listOf(
                navArgument("recordId"){ type = NavType.StringType}
            )
        ) { backStackEntry ->
            val attachmentViewModel: AttachmentViewModel = hiltViewModel(backStackEntry)
            AttachmentPage(attachmentViewModel)
        }
    }
}