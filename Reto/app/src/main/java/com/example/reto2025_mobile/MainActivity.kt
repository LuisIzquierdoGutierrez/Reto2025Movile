package com.example.reto2025_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.reto2025_mobile.Componentes.getLoginData
import com.example.reto2025_mobile.Navigation.NavManager
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ViewModel.GrupoParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfResponsableViewModel
import com.example.reto2025_mobile.ui.theme.Reto2025MobileTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setLocale("es")
        enableEdgeToEdge()
        setContent {
            Reto2025MobileTheme {
                val navController = rememberNavController()
                val profParticipanteViewModel by viewModels<ProfParticipanteViewModel>()
                val grupoParticipanteViewModel by viewModels<GrupoParticipanteViewModel>()
                val profResponsableViewModel by viewModels<ProfResponsableViewModel>()
                val actividadViewModel by viewModels<ActividadViewModel>()

                NavManager(navController, actividadViewModel, profParticipanteViewModel, grupoParticipanteViewModel, profResponsableViewModel)
            }
        }
    }

    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

}
