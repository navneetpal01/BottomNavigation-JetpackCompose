package com.example.bottomnavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.cources.CourseTabs
import com.example.bottomnavigation.nvgraph.NavGraph


@Composable
fun BottomNavigationApp(
    finishActivity: () -> Unit
) {
    val tabs = remember {
        CourseTabs.values()
    }
    val navController = rememberNavController()

    Scaffold(
        containerColor = Color.Blue,
        bottomBar = {}
    ) { padding ->
        NavGraph(
            finishActivity = finishActivity,
            navController = navController,
            modifier = Modifier.padding(padding)
        )
    }
}