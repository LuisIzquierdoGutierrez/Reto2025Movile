package com.example.reto2025_mobile.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.reto2025_mobile.Componentes.Usuario
import com.example.reto2025_mobile.Componentes.getLoginData
import com.example.reto2025_mobile.Componentes.saveLoginData
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ViewModel.FotoViewModel
import com.example.reto2025_mobile.ViewModel.GrupoParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfResponsableViewModel
import com.example.reto2025_mobile.ViewModel.ProfesorLoginViewModel
import com.example.reto2025_mobile.ViewModel.PuntosInteresViewModel
import com.example.reto2025_mobile.Views.ActividadesView
import com.example.reto2025_mobile.Views.CalendarView
import com.example.reto2025_mobile.Views.DetailsView
import com.example.reto2025_mobile.Views.FAQView
import com.example.reto2025_mobile.Views.HomeView
import com.example.reto2025_mobile.Views.LogginView
import com.example.reto2025_mobile.Views.PerfilView
import com.example.reto2025_mobile.Views.MisActividades
import com.example.reto2025_mobile.data.Profesor
import com.google.gson.Gson

@Composable
fun NavManager(
    navController: NavHostController,
    actividadViewModel: ActividadViewModel,
    profParticipanteViewModel: ProfParticipanteViewModel,
    grupoParticipanteViewModel: GrupoParticipanteViewModel,
    profResponsableViewModel: ProfResponsableViewModel,
    puntosInteresViewModel: PuntosInteresViewModel,
    fotoViewModel: FotoViewModel
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
            ActividadesView(navController, actividadViewModel, profResponsableViewModel, grupoParticipanteViewModel)
        }
        composable("misActividades"){
            MisActividades(navController, actividadViewModel, profParticipanteViewModel, grupoParticipanteViewModel)
        }
        composable("details"){
            DetailsView(navController, actividadViewModel, profParticipanteViewModel, grupoParticipanteViewModel, puntosInteresViewModel, fotoViewModel)
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






