package com.am_apps.recorded_money.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.am_apps.recorded_money.features.attachments.presentation.pages.AttachmentPage
import com.am_apps.recorded_money.features.attachments.presentation.view_model.AttachmentViewModel
import com.am_apps.recorded_money.features.tasks.presentation.composables.LocalTaskViewModel
import com.am_apps.recorded_money.features.tasks.presentation.pages.TasksPage
import com.am_apps.recorded_money.features.tasks.presentation.view_model.TasksViewModel

@Composable
fun RecordPageNavHost(
    recordId: Long,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.TaskPage.withArgs(recordId.toString()),
        modifier = modifier
    ) {
        composable(
            Screen.TaskPage.route + "/{recordId}",
            arguments = listOf(
                navArgument("recordId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val taskViewModel: TasksViewModel = hiltViewModel(backStackEntry)
            CompositionLocalProvider(LocalTaskViewModel provides taskViewModel) {
                TasksPage()
            }
        }
        composable(
            Screen.AttachmentPage.route + "/{recordId}",
            arguments = listOf(
                navArgument("recordId") { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val attachmentViewModel: AttachmentViewModel = hiltViewModel(backStackEntry)
            AttachmentPage(attachmentViewModel)
        }
    }
}