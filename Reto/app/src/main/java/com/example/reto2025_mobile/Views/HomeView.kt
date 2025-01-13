package com.example.reto2025_mobile.Views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reto2025_mobile.Componentes.AppBar
import com.example.reto2025_mobile.Componentes.BottomAppBar
import com.example.reto2025_mobile.Componentes.HomeAppBar

@Composable
fun HomeView(navController: NavController) {
    Scaffold (
        topBar = { HomeAppBar() },
        bottomBar = { BottomAppBar(navController = navController) }
    ){
        innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(1000.dp)) {

                Spacer(modifier = Modifier.height(20.dp))
                /*Button(
                    onClick = { navController.navigate("proximas") },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("PROXIMAS")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { navController.navigate("actividades") },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("ACTIVIDADES")
                }
                Spacer(modifier = Modifier.height(20.dp))*/
            }
        }
    }
}


