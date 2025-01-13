package com.example.reto2025_mobile.Views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reto2025_mobile.Componentes.AppBar
import com.example.reto2025_mobile.Componentes.BottomAppBar
import com.example.reto2025_mobile.Componentes.DetailTopBar
import com.example.reto2025_mobile.Componentes.HomeAppBar

@Composable
fun DetailsView(navController: NavController) {
    Scaffold (
        topBar = { DetailTopBar(navController = navController) }
    ){
            innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(1000.dp)) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Detalles", modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}