package com.example.reto2025_mobile.Views

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reto2025_mobile.Componentes.ActividadesTopAppBar
import com.example.reto2025_mobile.Componentes.AppBar
import com.example.reto2025_mobile.Componentes.BottomAppBar
import com.example.reto2025_mobile.Componentes.SelectColor
import com.example.reto2025_mobile.Componentes.Usuario
import com.example.reto2025_mobile.Componentes.normalizeString
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ViewModel.GrupoParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfParticipanteViewModel
import com.example.reto2025_mobile.data.Actividad
import com.example.reto2025_mobile.data.GrupoParticipante
import com.example.reto2025_mobile.data.ProfParticipante
import com.example.reto2025_mobile.ui.theme.BlueContainer

@Composable
fun MisActividades(
    navController: NavController,
    actividadViewModel: ActividadViewModel,
    profParticipanteViewModel: ProfParticipanteViewModel,
    grupoParticipanteViewModel: GrupoParticipanteViewModel
) {
    val grupoParticipantes: List<GrupoParticipante> by grupoParticipanteViewModel.gruposParticipantes.observeAsState(
        emptyList()
    )
    val profesParticipantes: List<ProfParticipante> by profParticipanteViewModel.profesoresParticipantes.observeAsState(
        emptyList()
    )

    profParticipanteViewModel.getProfesoresParticipantes()
    grupoParticipanteViewModel.getGruposParticipantes()


    val actividades: List<Actividad> by actividadViewModel.actividades.observeAsState(emptyList())


    val estados: List<String> =
        listOf("APROBADA", "CANCELADA", "REALIZADA", "SOLICITADA", "DENEGADA", "REALIZANDOSE")



    Scaffold(
        topBar = { ActividadesTopAppBar(navController) },
        bottomBar = { BottomAppBar(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    onClick = {
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Spacer(modifier = Modifier.width(25.dp))
                        Text(
                            text = "MIS ACTIVIDADES SOLICITADAS",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000000),
                            modifier = Modifier.padding(bottom = 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                LazyColumn {
                    items(actividades) { actividad ->
                        var color = SelectColor(actividad.estado)
                        if (actividad.solicitante.uuid == Usuario.uuid) {
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                                    .fillMaxHeight()
                                    .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = color),
                                onClick = {
                                    actividadViewModel.getActividadById(actividad.id)
                                    navController.navigate("details")
                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {

                                    Spacer(modifier = Modifier.width(25.dp))
                                    Text(
                                        text = actividad.titulo,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF000000),
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .fillMaxHeight(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    onClick = {
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Spacer(modifier = Modifier.width(25.dp))
                        Text(
                            text = "ACTIVIDADES EN LAS QUE PARTICIPO",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000000),
                            modifier = Modifier.padding(bottom = 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                LazyColumn {
                    items(profesParticipantes) { actividad ->
                        var color = SelectColor(actividad.actividad.estado)
                        if (actividad.profesor.uuid == Usuario.uuid) {
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                                    .fillMaxHeight()
                                    .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = color),
                                onClick = {
                                    actividadViewModel.getActividadById(actividad.id)
                                    navController.navigate("details")
                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {

                                    Spacer(modifier = Modifier.width(25.dp))
                                    Text(
                                        text = actividad.actividad.titulo,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF000000),
                                        modifier = Modifier.padding(bottom = 8.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
// dialogs de filtrado


    BackHandler {
        navController.navigate("home")
    }
}