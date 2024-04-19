package com.example.bottomnavigation.nvgraph

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.bottomnavigation.cources.CourseTabs
import com.example.bottomnavigation.cources.courses
import com.example.bottomnavigation.detail.DetailsScreen
import com.example.bottomnavigation.onboarding.OnBoardingScreen


@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Route.OnBoarding.screenName,
    finishActivity: () -> Unit
) {

    val actions = remember(navController){MainActions(navController)}
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Route.OnBoarding.screenName) {backStackEntry : NavBackStackEntry ->
            BackHandler {
                finishActivity()
            }
            OnBoardingScreen(
                onboardingCompleted = { actions.onboardingComplete(backStackEntry) }
            )
        }
        navigation(
            route = Route.Courses.screenName,
            startDestination = CourseTabs.FEATURED.route
        ) {
            courses()
        }
        composable(route = Route.Details.screenName) {
            DetailsScreen()
        }
    }
}

class MainActions(navController: NavHostController) {
    val onboardingComplete: (NavBackStackEntry) -> Unit = {from ->
        if (from.lifecycleIsResumed()){
            navController.navigate(Route.Courses.screenName)
        }
    }
}


private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED