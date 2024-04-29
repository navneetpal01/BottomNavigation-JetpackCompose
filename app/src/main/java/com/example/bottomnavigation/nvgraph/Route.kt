package com.example.bottomnavigation.nvgraph

sealed class Route(
    val screenName : String
){
    object OnBoarding: Route(screenName = "OnBoardingScreen")
    object Courses : Route(screenName = "HomeScreen")
    object Details : Route(screenName = "DetailsScreen")
}