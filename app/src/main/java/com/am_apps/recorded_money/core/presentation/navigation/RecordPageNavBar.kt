package com.am_apps.recorded_money.core.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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
fun RecordPageNavBar(navController: NavController, recordId: Long) {
    val primary = MaterialTheme.colorScheme.primary
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route ?: "unknown"
    NavigationBar(
        tonalElevation = 8.dp,
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.drawBehind {
            drawLine(
                color = primary,
                start = Offset(0f, 0f), // Top-left corner
                end = Offset(size.width, 0f), // Top-right corner
                strokeWidth = 1.dp.toPx()
            )
        }
    ) {
        recordPageRoutes.forEachIndexed { index, topLevelRoute ->
            val isSelected = currentDestination == topLevelRoute.screen.route + "/{recordId}"
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(topLevelRoute.iconId),
                        tint = if (isSelected) primary else MaterialTheme.colorScheme.secondary,
                        contentDescription = topLevelRoute.name
                    )
                },
                label = { Text(topLevelRoute.name) },
                selected = isSelected,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = primary,
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                ),
                alwaysShowLabel = isSelected,
                onClick = {
                    if (!isSelected) {
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
                }
            )
        }
    }
}