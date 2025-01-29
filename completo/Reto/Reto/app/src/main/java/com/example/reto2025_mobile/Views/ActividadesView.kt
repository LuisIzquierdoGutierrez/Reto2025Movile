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
import com.example.reto2025_mobile.Componentes.BottomAppBar
import com.example.reto2025_mobile.Componentes.SelectColor
import com.example.reto2025_mobile.Componentes.normalizeString
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ViewModel.GrupoParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfResponsableViewModel
import com.example.reto2025_mobile.data.Actividad
import com.example.reto2025_mobile.data.GrupoParticipante
import com.example.reto2025_mobile.data.ProfParticipante
import com.example.reto2025_mobile.data.ProfResponsable

@Composable
fun ActividadesView(
    navController: NavController,
    actividadViewModel: ActividadViewModel,
    profResponsableViewModel: ProfResponsableViewModel,
    grupoParticipanteViewModel: GrupoParticipanteViewModel
) {
    val grupoParticipantes: List<GrupoParticipante> by grupoParticipanteViewModel.gruposParticipantes.observeAsState(
        emptyList()
    )
    val profesResponsables: List<ProfResponsable> by profResponsableViewModel.profesoresResponsables.observeAsState(
        emptyList()
    )

    val context = LocalContext.current

    val actividades: List<Actividad> by actividadViewModel.actividades.observeAsState(emptyList())

    var actividFiltered: MutableList<Actividad> by remember { mutableStateOf(mutableListOf()) }

    var showActividades: List<Actividad> by remember { mutableStateOf(emptyList()) }

    showActividades = actividades

    var estados: MutableSet<String> by remember { mutableStateOf(mutableSetOf()) }
    for(actividad in actividades){
        estados.add(actividad.estado)
    }

    var expEstado by remember { mutableStateOf(false) }
    /*var expEtapa by remember { mutableStateOf(false) }
    var expDep by remember { mutableStateOf(false) }*/

    profResponsableViewModel.getProfesoresResponsables()
    grupoParticipanteViewModel.getGruposParticipantes()

    Scaffold(
        topBar = { ActividadesTopAppBar(navController) },
        bottomBar = { BottomAppBar(navController = navController) },
        containerColor = Color.LightGray
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                    Button(
                        onClick = {
                            actividFiltered = actividades.toMutableList()
                            showActividades = actividFiltered
                        },
                        modifier = Modifier.weight(0.2f)
                    )
                    {
                        Text("Todas")
                    }
                    Button(
                        onClick = { expEstado = true },
                        modifier = Modifier.weight(0.2f)
                    ) {
                        Text("Estado")
                    }/*
                    Button(
                        onClick = { expDep = true },
                        modifier = Modifier.weight(0.2f)
                    ) {
                        Text("Depart.")
                    }
                    Button(
                        onClick = { expEtapa = true },
                        modifier = Modifier.weight(0.2f)
                    ) {
                        Text("Etapa")
                    }*/
                    if (expEstado) {
                        AlertDialog(
                            onDismissRequest = { expEstado = false },
                            confirmButton = { },
                            title = {
                                Text("Filtrar por Estado")
                            },
                            text = {
                                LazyColumn {
                                    items(estados.toList()) { est ->
                                        Button(
                                            onClick = {
                                                expEstado = false
                                                actividFiltered.clear()
                                                // filtrar actividades por estado
                                                for (actividad in actividades) {
                                                    if (actividad.estado.equals(
                                                            est,
                                                            ignoreCase = true
                                                        )
                                                    ) {
                                                        actividFiltered.add(actividad)
                                                    }
                                                }
                                                showActividades = actividFiltered

                                                if (actividFiltered.isEmpty()) {
                                                    Toast.makeText(
                                                        context,
                                                        "No hay actividades para esa etapa",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            },
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Text(est)
                                        }
                                    }
                                }
                            }
                        )
                    }
                    /*val uniqueDep = mutableSetOf<Int>()
                    if (expDep) {
                        AlertDialog(
                            onDismissRequest = { expDep = false },
                            confirmButton = { },
                            title = {
                                Text("Filtrar por departamento")
                            },
                            text = {
                                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                                    profesResponsables.forEach { prp ->
                                        if (uniqueDep.add(prp.profesor.depart.id))
                                            Button(
                                                onClick = {
                                                    actividFiltered.clear()
                                                    expDep = false
                                                    // filtrar actividades por etapa
                                                    for (prf in profesResponsables) {
                                                        if (prf.profesor.depart.id == prp.profesor.depart.id) {
                                                            actividFiltered.add(prf.actividad)

                                                        }
                                                    }
                                                    showActividades = actividFiltered
                                                    if (actividFiltered.isEmpty()) {
                                                        Toast.makeText(
                                                            context,
                                                            "No hay actividades para ese Departamento",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                },
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                Text(prp.profesor.depart.nombre)
                                            }

                                    }
                                }
                            }
                        )
                    }
                    val uniqueEtapa = mutableSetOf<String>()

                    if (expEtapa) {
                        AlertDialog(
                            onDismissRequest = { expEtapa = false },
                            confirmButton = { },
                            title = {
                                Text("Filtrar por etapa")
                            },
                            text = {
                                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                                    grupoParticipantes.forEach { grp ->
                                        if (uniqueEtapa.add(grp.grupo.curso.etapa))
                                            Button(
                                                onClick = {
                                                    actividFiltered.clear()
                                                    expEtapa = false
                                                    // filtrar actividades por etapa
                                                    for (grupos in grupoParticipantes) {
                                                        if (grupos.grupo.curso.etapa == grp.grupo.curso.etapa) {
                                                            actividFiltered.add(grupos.actividades)

                                                        }
                                                    }
                                                    showActividades = actividFiltered
                                                    if (actividFiltered.isEmpty()) {
                                                        Toast.makeText(
                                                            context,
                                                            "No hay actividades para ese grupo",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                },
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                Text(grp.grupo.curso.etapa)
                                            }

                                    }
                                }
                            }
                        )
                    }*/
                }
                Row(modifier = Modifier.fillMaxWidth()) {

                    var busqueda by remember { mutableStateOf("") }
                    var normalizedBusqueda = normalizeString(busqueda)
                    OutlinedTextField(
                        value = busqueda,
                        onValueChange = { busqueda = it },
                        label = { Text("Buscar") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier
                            .weight(0.6f)
                            .padding(10.dp)
                    )
                    IconButton(
                        onClick = {
                            showActividades = actividades.filter {
                                normalizeString(it.titulo).contains(normalizedBusqueda)
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Busqueda",
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn {
                    items(showActividades) { actividad ->
                        val color = SelectColor(actividad.estado)
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
        }
    }
// dialogs de filtrado


    BackHandler {
        navController.navigate("home")
    }
}

