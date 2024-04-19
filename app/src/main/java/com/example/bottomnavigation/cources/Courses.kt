package com.example.bottomnavigation.cources

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.bottomnavigation.R


fun NavGraphBuilder.courses(){
    composable(route = CourseTabs.FEATURED.route){
        FeaturedCoursesScreen()
    }
    composable(route = CourseTabs.MY_COURSES.route){
        MyCoursesScreen()
    }
    composable(route = CourseTabs.SEARCH.route){
        SearchCoursesScreen()
    }
}


enum class CourseTabs(
    @StringRes val title : Int,
    @DrawableRes val icon : Int,
    val route : String
){
    MY_COURSES(R.string.my_courses,R.drawable.ic_grain,CoursesDestinations.MY_COURSES_ROUTE),
    FEATURED(R.string.featured,R.drawable.ic_featured,CoursesDestinations.FEATURED_ROUTE),
    SEARCH(R.string.search,R.drawable.ic_search,CoursesDestinations.SEARCH_COURSES_ROUTE)
}


private object CoursesDestinations{
    const val FEATURED_ROUTE = "courses/featured"
    const val MY_COURSES_ROUTE = "courses/my"
    const val SEARCH_COURSES_ROUTE = "courses/search"
}