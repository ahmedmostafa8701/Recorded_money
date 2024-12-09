package com.am_apps.recorded_money.core.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun RecordPageNavBar(navController: NavController, recordId : Long){
    val primary = MaterialTheme.colorScheme.primary
    BottomNavigation(
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.drawBehind {
            drawLine(
                color = primary,
                start = Offset(0f, 0f), // Top-left corner
                end = Offset(size.width, 0f), // Top-right corner
                strokeWidth = 1.dp.toPx()
            )
        }
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        recordPageRoutes.forEach { topLevelRoute ->
            BottomNavigationItem(
                icon = { Icon(painterResource(topLevelRoute.iconId), contentDescription = topLevelRoute.name) },
                label = { Text(topLevelRoute.name) },
                selected = true,
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.secondary,
                alwaysShowLabel = true,
                onClick = {
                    if (currentDestination?.route != topLevelRoute.screen.route)
                    navController.navigate(topLevelRoute.screen.withArgs(recordId.toString())) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}