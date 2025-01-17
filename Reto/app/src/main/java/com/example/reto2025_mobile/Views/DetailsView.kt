package com.example.reto2025_mobile.Views

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.reto2025_mobile.Componentes.DetailTopBar
import com.example.reto2025_mobile.Componentes.Fotos
import com.example.reto2025_mobile.Componentes.MapScreen
import com.example.reto2025_mobile.Componentes.Mapa
import com.example.reto2025_mobile.R
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ViewModel.GrupoParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfParticipanteViewModel
import com.example.reto2025_mobile.data.Actividad
import com.example.reto2025_mobile.data.GrupoParticipante
import com.example.reto2025_mobile.data.ProfParticipante

@Composable
fun DetailsView(
    navController: NavController,
    actividadViewModel: ActividadViewModel,
    profParticipanteViewModel: ProfParticipanteViewModel,
    grupoParticipanteViewModel: GrupoParticipanteViewModel
) {
    val profParticipantes: List<ProfParticipante> by profParticipanteViewModel.profesoresParticipantes.observeAsState(emptyList())
    val grupoParticipantes: List<GrupoParticipante> by grupoParticipanteViewModel.gruposParticipantes.observeAsState(emptyList())
    val actividad: Actividad? by actividadViewModel.actividad.observeAsState()
    var color by remember { mutableStateOf(Color(0xFFD0E8F2)) }
    actividad?.let {
        Scaffold(
            topBar = { DetailTopBar(navController = navController, it.titulo) }
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
                        if(it.estado == "SOLICITADA"){
                            color = Color(0xFFD0E8F2)
                        }else if(it.estado == "DENEGADA"){
                            color = Color(0xFFCD5C5C)
                        }else if(it.estado == "APROBADA"){
                            color = Color(0xFFADD8E6)
                        }else if(it.estado == "REALIZADA"){
                            color = Color(0xFF90EE90)
                        }else if(it.estado == "REALIZANDOSE"){
                            color = Color(0xFFFFFFE0)
                        }else if(it.estado == "CANCELADA"){
                            color = Color(0xFFD3D3D3)
                        }

                        LazyColumn {
                            item{
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
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
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
                            item {
                                if (it.urlFolleto != null) {
                                    val context = LocalContext.current
                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color(0xFFB0C4DE)),
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
                            }
                            item{
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
                                ) {
                                    Text(
                                        text = "Fecha inicio: ${it.fini}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    Text(
                                        text = "Fecha finalizacion ${it.ffin}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                            item{
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
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
                            item{
                                Card(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
                                ) {
                                    Text(
                                        text = "Profesor solicitante: ${it.solicitante.nombre} ${it.solicitante.apellidos}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                            item{
                                if (it.incidencias != null) {
                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
                                    ) {
                                        Text(
                                            text = "Incidencias: ${it.incidencias}",
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                    }
                                }
                            }
                            item{
                                var trans = "No"
                                if (it.transporteReq) {
                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
                                    ) {
                                        trans = "Si"
                                        Text(
                                            text = "Transporte ${trans}",
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                        if(it.comentTransporte != null) {
                                            Text(
                                                text = "${it.comentTransporte}",
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(8.dp)
                                            )
                                        }
                                    }
                                }
                            }
                            item{
                                var aloj = "No"
                                if (it.alojamientoReq) {
                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxWidth(),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
                                    ) {
                                        aloj = "Si"
                                        Text(
                                            text = "Alojamiento ${aloj}",
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(8.dp)
                                        )
                                        if(it.comentAlojamiento != null) {
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
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
                                ) {
                                    Text(
                                        text = "Profesores participantes: ",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    for(prof in profParticipantes){
                                        if(prof.actividad.id == it.id){
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
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
                                ) {
                                    Text(
                                        text = "Grupos participantes: ",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                    for(grupo in grupoParticipantes){
                                        if(grupo.actividades.id == it.id){
                                            Text(
                                                text = grupo.grupo.codGrupo,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(8.dp)
                                            )
                                        }
                                    }

                                }
                            }
                            item {
                                Column {
                                    val selectedImageUris = remember { mutableStateListOf<Uri?>() }
                                    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
                                        contract = ActivityResultContracts.PickMultipleVisualMedia(),
                                        onResult = { uris ->
                                            uris.forEach { uri ->
                                                uri?.let { selectedImageUris.add(it) }
                                            }
                                        }
                                    )
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
                                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
                                                ) {
                                                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                                        Icon(
                                                            imageVector = ImageVector.vectorResource(R.drawable.photo),
                                                            contentDescription = "añadir imagenes",
                                                            modifier = Modifier.size(32.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            item {
                                var showMap by remember { mutableStateOf(false) }
                                var showPhoto by remember { mutableStateOf(false) }

                                Row {
                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .weight(0.5f),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2)),
                                        onClick = { showPhoto = true }

                                    ) {
                                        if (showPhoto) Fotos(onDismiss = { showPhoto = false })
                                        Row {
                                            Icon(imageVector = ImageVector.vectorResource(R.drawable.addphoto), contentDescription = "photo", modifier = Modifier.padding(8.dp))
                                            Text(text = "Añadir imagenes", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
                                        }
                                    }
                                    Card(
                                            modifier = Modifier
                                                .padding(8.dp)
                                                .weight(0.5f),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2)),
                                    onClick = { showMap = true }

                                    ) {
                                    if (showMap) Mapa(onDismiss = { showMap = false })
                                    Row {
                                        Icon(Icons.Default.LocationOn, contentDescription = "Ubicacion", modifier = Modifier.padding(8.dp))
                                        Text(text = "Ubicacion", fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
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
