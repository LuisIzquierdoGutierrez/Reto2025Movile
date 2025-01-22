package com.example.reto2025_mobile.Navigation

import android.widget.CalendarView
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ViewModel.GrupoParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfParticipanteViewModel
import com.example.reto2025_mobile.Views.ActividadesView
import com.example.reto2025_mobile.Views.CalendarView
import com.example.reto2025_mobile.Views.DetailsView
import com.example.reto2025_mobile.Views.FAQView
import com.example.reto2025_mobile.Views.HomeView
import com.example.reto2025_mobile.Views.LogginView
import com.example.reto2025_mobile.Views.PerfilView
import com.example.reto2025_mobile.Views.ProximasView


@Composable
fun NavManager(
    navController: NavHostController,
    actividadViewModel: ActividadViewModel,
    profParticipanteViewModel: ProfParticipanteViewModel,
    grupoParticipanteViewModel: GrupoParticipanteViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "loggin"
    ) {
        composable("loggin"){
            LogginView(navController)
        }
        composable("home"){
            HomeView(navController, actividadViewModel, profParticipanteViewModel, grupoParticipanteViewModel)
        }
        composable("actividades"){
            ActividadesView(navController, actividadViewModel, profParticipanteViewModel, grupoParticipanteViewModel)
        }
        composable("proximas"){
            ProximasView(navController)
        }
        composable("details"){
            DetailsView(navController, actividadViewModel, profParticipanteViewModel, grupoParticipanteViewModel)
        }
        composable("FAQ"){
            FAQView(navController)
        }
        composable("perfil"){
            PerfilView(navController)
        }

        composable("calendario"){
            CalendarView(navController, actividadViewModel, profParticipanteViewModel, grupoParticipanteViewModel)
        }
    }
}






