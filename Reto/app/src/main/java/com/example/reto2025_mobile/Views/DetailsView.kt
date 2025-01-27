package com.example.reto2025_mobile.Views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reto2025_mobile.Componentes.BottomDetailBar
import com.example.reto2025_mobile.Componentes.DetailTopBar
import com.example.reto2025_mobile.Componentes.Pics
import com.example.reto2025_mobile.Componentes.SelectColor
import com.example.reto2025_mobile.Componentes.Usuario
import com.example.reto2025_mobile.Componentes.formatFecha
import com.example.reto2025_mobile.R
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ViewModel.GrupoParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.PuntosInteresViewModel
import com.example.reto2025_mobile.data.Actividad
import com.example.reto2025_mobile.data.GrupoParticipante
import com.example.reto2025_mobile.data.ProfParticipante
import com.example.reto2025_mobile.ui.theme.BlueContainer

@Composable
fun DetailsView(
    navController: NavController,
    actividadViewModel: ActividadViewModel,
    profParticipanteViewModel: ProfParticipanteViewModel,
    grupoParticipanteViewModel: GrupoParticipanteViewModel,
    puntosInteresViewModel: PuntosInteresViewModel
) {
    val profParticipantes: List<ProfParticipante> by profParticipanteViewModel.profesoresParticipantes.observeAsState( emptyList() )
    val grupoParticipantes: List<GrupoParticipante> by grupoParticipanteViewModel.gruposParticipantes.observeAsState( emptyList() )

//profParticipanteViewModel.getProfesoresParticipantes()
    val actividad: Actividad? by actividadViewModel.actividad.observeAsState()
    var enableUpdate by remember { mutableStateOf(false) }
    // datos de la actividad
    var incidencias by remember { mutableStateOf(actividad?.incidencias ?: "") }
    var participantes: MutableSet<String> = mutableSetOf()

    for (prof in profParticipantes) {
        if (prof.actividad.id == actividad?.id && prof.profesor.uuid == Usuario.uuid) {
            participantes.add(prof.profesor.uuid)
            enableUpdate = true
            break
        }
    }


    actividad?.let {

        Scaffold(
            topBar = {
                DetailTopBar(navController = navController)
            },
            bottomBar = {
                BottomDetailBar(actividad = actividad!!, profParticipantes = profParticipantes, puntosInteresViewModel = puntosInteresViewModel, participantes = participantes)
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1000.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {

                        val color = SelectColor(actividad!!.estado)

                        LazyColumn {
                            item {
                                var showPic by remember { mutableStateOf(false) }
                                Column {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp)
                                    ) {
                                        LazyRow {
                                            items(10) {
                                                Card(
                                                    modifier = Modifier
                                                        .padding(3.dp)
                                                        .fillMaxHeight()
                                                        .width(60.dp),
                                                    shape = RoundedCornerShape(12.dp),
                                                    colors = CardDefaults.cardColors(containerColor = BlueContainer),
                                                    onClick = { showPic = true }
                                                ) {

                                                    Box(
                                                        modifier = Modifier.fillMaxSize(),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Icon(
                                                            imageVector = ImageVector.vectorResource(
                                                                R.drawable.photo
                                                            ),
                                                            contentDescription = "añadir imagenes",
                                                            modifier = Modifier.size(32.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                        if (showPic) Pics(onDismiss = { showPic = false })
                                    }
                                }
                            }

                            item {
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = BlueContainer)
                                ) {
                                    Text(
                                        text = it.titulo,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                            item {
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = color)
                                ) {

                                    Text(
                                        text = "Estado: ${it.estado}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    if (it.comentEstado != null) {
                                        Text(
                                            text = it.comentEstado.toString(),
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }
                                }
                            }
                            item {
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = BlueContainer)
                                ) {
                                    Text(
                                        text = it.descripcion ?: "",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = "Actividad ${it.tipo}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        text = "Comentarios: ${it.comentarios}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                            /*item {
                                if (it.urlFolleto != null) {
                                    val context = LocalContext.current
                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = GreenContainer),
                                        onClick = {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.urlFolleto))
                                            context.startActivity(intent)
                                        }
                                    ) {
                                        Text(
                                            text = "Sitio Web",
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }
                                }
                            }*/
                            item {
                                val fechaInicio = formatFecha(it.fini)
                                val fechaFin = formatFecha(it.ffin)

                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = BlueContainer)
                                ) {
                                    Text(
                                        text = "Fecha inicio: ${fechaInicio}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Text(
                                        text = "Fecha finalizacion ${fechaFin}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                            item {
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = BlueContainer)
                                ) {
                                    Text(
                                        text = "Hora inicio: ${it.hini}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Text(
                                        text = "Hora finalizacion ${it.hfin}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                            item {
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = BlueContainer)
                                ) {
                                    Text(
                                        text = "Profesor solicitante: ${it.solicitante.nombre} ${it.solicitante.apellidos}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                            item {
                                var enabled by remember { mutableStateOf(false) }

                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = BlueContainer)
                                ) {
                                    Row(modifier = Modifier.fillMaxWidth()){
                                        Text(
                                            text = "Incidencias:",
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                        Spacer(modifier = Modifier.width(230.dp))
                                        Switch(
                                            enabled = enableUpdate,
                                            checked = enabled,
                                            onCheckedChange = { enabled = it },
                                            modifier = Modifier.padding(8.dp).size(20.dp)
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(2.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        OutlinedTextField(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .border(
                                                    0.dp,
                                                    Color.Black,
                                                    RoundedCornerShape(12.dp)
                                                ),
                                            value = incidencias,
                                            onValueChange = { incidencias = it },
                                            textStyle = TextStyle(
                                                color = Color.Black,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            ),

                                            enabled = enabled,
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                        )
                                    }
                                    Button(
                                        onClick = {
                                            val updateActividad =
                                                actividad!!.copy(incidencias = incidencias)
                                            actividadViewModel.updateActividad(updateActividad)
                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 20.dp),  // Padding superior para el botón
                                        shape = RoundedCornerShape(50.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFFB0D0E0),  // Verde moderno
                                            contentColor = Color.Black  // Texto blanco
                                        )
                                    ) {
                                        Text(
                                            text = "Añadir incidencia",
                                            fontWeight = FontWeight.Bold
                                        )
                                    }




                                }

                            }
                            item {
                                var trans = "No"
                                if (it.transporteReq) {
                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = BlueContainer)
                                    ) {
                                        trans = "Si"
                                        Text(
                                            text = "Transporte ${trans}",
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                        if (it.comentTransporte != null) {
                                            Text(
                                                text = "${it.comentTransporte}",
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(8.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            item {
                                var aloj = "No"
                                if (it.alojamientoReq) {
                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = BlueContainer)
                                    ) {
                                        aloj = "Si"
                                        Text(
                                            text = "Alojamiento ${aloj}",
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                        if (it.comentAlojamiento != null) {
                                            Text(
                                                text = "${it.comentAlojamiento}",
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(8.dp)
                                            )
                                        }

                                    }
                                }
                            }
                            item {
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = BlueContainer)
                                ) {
                                    Text(
                                        text = "Profesores participantes: ",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    for (prof in profParticipantes) {
                                        if (prof.actividad.id == it.id) {
                                            Text(
                                                text = "${prof.profesor.nombre} ${prof.profesor.apellidos}",
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(8.dp)
                                            )
                                        }
                                    }

                                }
                            }
                            item {

                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = BlueContainer)
                                ) {
                                    Text(
                                        text = "Grupos participantes: ",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    for (grupo in grupoParticipantes) {
                                        var numPart by remember { mutableStateOf(grupo.numParticipantes) }
                                        var coment by remember { mutableStateOf(grupo.comentario) }
                                        var enable by remember { mutableStateOf(false) }

                                        if (grupo.actividades.id == it.id) {
                                            Column {
                                                Row(modifier = Modifier.fillMaxWidth()) {
                                                    Text(
                                                        text = grupo.grupo.codGrupo,
                                                        fontWeight = FontWeight.Bold,
                                                        modifier = Modifier.padding(8.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(270.dp))
                                                    Switch(
                                                        enabled = enableUpdate,
                                                        checked = enable,
                                                        onCheckedChange = { enable = it },
                                                        modifier = Modifier.padding(8.dp).size(20.dp)
                                                    )
                                                }

                                                Text(
                                                    text = "Numero de asistentes",
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(8.dp)
                                                )
                                                OutlinedTextField(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .border(
                                                            0.dp,
                                                            Color.Black,
                                                            RoundedCornerShape(12.dp)
                                                        ),
                                                    value = numPart.toString(),
                                                    onValueChange = { numPart = it.toInt() },
                                                    textStyle = TextStyle(
                                                        color = Color.Black,
                                                        fontSize = 16.sp,
                                                        fontWeight = FontWeight.Bold
                                                    ),
                                                    enabled = enable,
                                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                                )

                                                Text(
                                                    text = "Comentarios:",
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(8.dp)
                                                )
                                                OutlinedTextField(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .border(
                                                            0.dp,
                                                            Color.Black,
                                                            RoundedCornerShape(12.dp)
                                                        ),
                                                    value = coment?:"",
                                                    onValueChange = { coment = it },
                                                    textStyle = TextStyle(
                                                        color = Color.Black,
                                                        fontSize = 16.sp,
                                                        fontWeight = FontWeight.Bold
                                                    ),
                                                    enabled = enable,
                                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                                )
                                                Button(
                                                    onClick = {
                                                        val updateGrupoParticipante =
                                                            grupo.copy(
                                                                numParticipantes = numPart,
                                                                comentario = coment
                                                            )
                                                        grupoParticipanteViewModel.updateGrupoParticipante(updateGrupoParticipante)


                                                    },
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(top = 8.dp),  // Padding superior para el botón
                                                    shape = RoundedCornerShape(50.dp),
                                                    colors = ButtonDefaults.buttonColors(
                                                        containerColor = Color(0xFFB0D0E0),  // Verde moderno
                                                        contentColor = Color.Black  // Texto blanco
                                                    )
                                                ) {
                                                    Text(
                                                        text = "Guardar cambios",
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                            }


                                        }
                                    }

                                }
                            }

                        }
                    }
                    Box(modifier = Modifier.weight(0.5f)) {

                    }
                }
            }
        }
    }
    BackHandler {
        navController.popBackStack()
    }
}
