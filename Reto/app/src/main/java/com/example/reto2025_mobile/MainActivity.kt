package com.example.reto2025_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
<<<<<<< Updated upstream
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
=======
>>>>>>> Stashed changes
import androidx.navigation.compose.rememberNavController
import com.example.appgatos.navigation.NavManager
import com.example.reto2025_mobile.Views.LogginView
import com.example.reto2025_mobile.ui.theme.Reto2025MobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Reto2025MobileTheme {
                val navController = rememberNavController()
<<<<<<< Updated upstream
                NavManager(navController = navController)
=======
                NavManager(navController)
>>>>>>> Stashed changes
            }
        }
    }
}
