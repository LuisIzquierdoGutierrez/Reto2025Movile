package com.example.reto2025_mobile.Views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reto2025_mobile.Componentes.AppBar

@Composable
fun HomeView(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppBar()
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("actividades") },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("VER ACTIVIDADES")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {  },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("BUSCAR ACTIVIDAD")
            }

        }
    }
}


