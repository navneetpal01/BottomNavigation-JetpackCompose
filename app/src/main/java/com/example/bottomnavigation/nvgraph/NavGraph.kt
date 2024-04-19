package com.example.bottomnavigation.nvgraph

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
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
    navController : NavHostController = rememberNavController(),
    startDestination : String = Route.OnBoarding.screenName,
    finishActivity : () -> Unit
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ){
        composable(route = Route.OnBoarding.screenName){
            OnBoardingScreen(
                navController = navController
            )
        }
        navigation(
            route = Route.Courses.screenName,
            startDestination = CourseTabs.FEATURED.route
        ){
            courses()
        }
        composable(route = Route.Details.screenName){
            DetailsScreen()
        }
    }
}