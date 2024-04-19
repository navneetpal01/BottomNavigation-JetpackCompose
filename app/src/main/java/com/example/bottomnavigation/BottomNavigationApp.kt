package com.example.bottomnavigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.cources.CourseTabs
import com.example.bottomnavigation.nvgraph.NavGraph
import com.example.bottomnavigation.ui.theme.BottomNavigationTheme
import java.util.Locale


@Composable
fun BottomNavigationApp(
    finishActivity: () -> Unit
) {
    BottomNavigationTheme {
        val tabs = remember {
            CourseTabs.values()
        }
        val navController = rememberNavController()

        Scaffold(
            containerColor = Color.Blue,
            bottomBar = {
                AppBottomBar(navController = navController, tabs = tabs)
            }
        ) { padding ->
            NavGraph(
                finishActivity = finishActivity,
                navController = navController,
                modifier = Modifier.padding(padding)
            )
        }
    }
}


@Composable
fun AppBottomBar(navController: NavHostController, tabs: Array<CourseTabs>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route ?: CourseTabs.FEATURED.route


    val routes = remember { CourseTabs.values().map { it.route } }

    if (currentRoute in routes) {
        NavigationBar(
            modifier = Modifier.windowInsetsBottomHeight(
                WindowInsets.navigationBars.add(WindowInsets(bottom = 56.dp))
            )
        ) {
            tabs.forEach { tab ->
                NavigationBarItem(
                    modifier = Modifier.navigationBarsPadding(),
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (tab.route != currentRoute) {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(painter = painterResource(id = tab.icon), contentDescription = "null")
                    },
                    label = {
                        Text(text = stringResource(id = tab.title).uppercase(locale = Locale.getDefault()))
                    },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.secondary,
                        unselectedIconColor = LocalContentColor.current
                    )
                )
            }
        }


    }


}