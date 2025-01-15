package com.example.reto2025_mobile.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.Views.ActividadesView
import com.example.reto2025_mobile.Views.DetailsView
import com.example.reto2025_mobile.Views.FAQView
import com.example.reto2025_mobile.Views.HomeView
import com.example.reto2025_mobile.Views.LogginView
import com.example.reto2025_mobile.Views.PicturesView
import com.example.reto2025_mobile.Views.ProximasView


@Composable
fun NavManager(navController: NavHostController, actividadViewModel: ActividadViewModel) {
    NavHost(
        navController = navController,
        startDestination = "loggin"
    ) {
        composable("loggin"){
            LogginView(navController)
        }
        composable("home"){
            HomeView(navController)
        }
        composable("actividades"){
            ActividadesView(navController, actividadViewModel)
        }
        composable("proximas"){
            ProximasView(navController)
        }
        composable("details"){
            DetailsView(navController)
        }
        composable("FAQ"){
            FAQView(navController)
        }
        composable("pictures"){
            PicturesView(navController)
        }
    }
}






