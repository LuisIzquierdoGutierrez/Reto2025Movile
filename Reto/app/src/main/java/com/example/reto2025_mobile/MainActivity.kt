package com.example.reto2025_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.reto2025_mobile.Navigation.NavManager
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ui.theme.Reto2025MobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Reto2025MobileTheme {
                val actividadViewModel by viewModels<ActividadViewModel>()
                val navController = rememberNavController()
                NavManager(navController, actividadViewModel)
            }
        }
    }
}
