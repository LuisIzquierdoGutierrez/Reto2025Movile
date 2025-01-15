package com.example.reto2025_mobile.Views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reto2025_mobile.Componentes.DetailTopBar
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.data.Actividad

@Composable
fun DetailsView(
    navController: NavController,
    actividadViewModel: ActividadViewModel
) {
    val actividad: Actividad? by actividadViewModel.actividad.observeAsState()
    actividad?.let {
        Scaffold (
            topBar = { DetailTopBar(navController = navController, it.titulo)  }
        ){
                innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(1000.dp)) {
                    Text(text = it.descripcion?:"", fontSize = 12.sp)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Tipo de actividad: ${it.tipo}")
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Fecha inicio: ${it.fini}")
                    Text(text = "Fecha finalizacion ${it.ffin}")
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Hora inicio: ${it.hini}")
                    Text(text = "Hora finalizacion ${it.hfin}")
                    Spacer(modifier = Modifier.height(5.dp))



                }
            }
        }
    }
    }
