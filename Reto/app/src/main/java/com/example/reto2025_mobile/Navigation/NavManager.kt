package com.example.appgatos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reto2025_mobile.Views.LogginView


@Composable
fun NavManager(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "principal"
    ) {
        composable("principal"){
            LogginView(navController)
        }

    }
}






