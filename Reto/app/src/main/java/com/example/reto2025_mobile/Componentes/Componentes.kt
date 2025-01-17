package com.example.reto2025_mobile.Componentes

import android.graphics.DashPathEffect
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.reto2025_mobile.Navigation.ItemsNav
import com.example.reto2025_mobile.R
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ViewModel.GrupoParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfParticipanteViewModel
import com.example.reto2025_mobile.data.Actividad
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import io.github.boguszpawlowski.composecalendar.Calendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import java.time.LocalDate

// Top Bar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                "Proximas",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {navController.popBackStack()}) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF4682B4),
            titleContentColor = Color.White
        )
    )
}

//Top bar de la pantalla de Detalles de una actividad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(navController: NavController, titulo: String) {
    var expanded by remember { mutableStateOf(false) }
    var showIncidencia by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                titulo,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {navController.popBackStack()}) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF4682B4),
            titleContentColor = Color.White
        ),
        actions = {
            Box {
                IconButton(onClick = { showIncidencia = true}) {
                    Icon(imageVector = Icons.Default.Create, contentDescription = "Incidencias")
                }
                if(showIncidencia) {
                    Incidencias(onDismiss = { showIncidencia = false })
                }
            }
        },
    )
}

//Top bar de la pantalla de Actividades

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActividadesTopAppBar(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                "ACTIVIDADES",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {navController.popBackStack()}) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF4682B4),
            titleContentColor = Color.White
        ),
        actions = {
            Box {
                Row {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.filter),
                            contentDescription = "Filtros"
                        )
                        if(expanded){
                            Filtros(onDismiss = { expanded = false })
                        }
                    }
                }
            }
        },
    )
}

//Top bar de la pantalla de Inicio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(navController: NavController) {
    var showlogout by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                "ACEX",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF4682B4),
            titleContentColor = Color.White
        ),
        actions = {
            Box {
                IconButton(onClick = { showlogout = true }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.logout),
                        contentDescription = "cerrar sesion"
                    )
                    if(showlogout){
                        AlertDialog(
                            onDismissRequest = {  },
                            confirmButton = {
                                Button(onClick = {
                                    navController.navigate("loggin")
                                    showlogout = false })
                                {
                                    Text("Aceptar")
                                }
                            },
                            dismissButton = {
                                Button(onClick = { showlogout = false }) {
                                    Text("Cancelar")
                                }
                            },
                            text = {
                                Text(text = "¿Desea cerrar sesion?")
                            }
                        )
                    }
                }
            }
        },
    )
}

// Bottom Bar con navegacion entre pantallas

@Composable
fun currentRoute(navController: NavController) :String? =
    navController.currentBackStackEntryAsState().value?.destination?.route

@Composable
fun BottomAppBar(navController: NavController) {
    val bar_items = listOf(
        ItemsNav.Item_bottom_nav_home,
        ItemsNav.Item_bottom_nav_acts,
        ItemsNav.Item_bottom_nav_faq
    )
    NavigationBar(
        containerColor = Color(0xFF4682B4),
        contentColor = Color.White
    ) {
        bar_items.forEach { item ->
            val clicked = currentRoute(navController) == item.ruta
            NavigationBarItem(
                selected = clicked,
                onClick = { navController.navigate(item.ruta) },
                icon = { Icon(imageVector = item.icono, contentDescription = null) },
                label = { Text(item.texto) }
            )
        }
    }
}

// Cuadros de dialogo para filtrar actividades y añadir incidencias

@Composable
fun Incidencias(onDismiss: () -> Unit) {
    var inci by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        text = {
            Column {
                Text(text = "Añadir incidencia")
                TextField(value = inci,
                    onValueChange = { inci = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    )
            }
        }
    )
}

@Composable
fun Mapa(onDismiss: () -> Unit) {
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .size(400.dp),
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Aceptar")
            }
        },
        text = {
            Column {
                Box(modifier = Modifier.size(300.dp)) {
                    MapScreen()
                }
            }
        }
    )
}

@Composable
fun Fotos(onDismiss: () -> Unit) {

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .size(800.dp),
        onDismissRequest = onDismiss,
        confirmButton = {
        },
        text = {
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
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(0.5f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            IconButton(onClick = {
                                multiplePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                ) }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.addphoto),
                                    contentDescription = "añadir imagenes",
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                    }

                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(0.5f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
                    ) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.save),
                                    contentDescription = "subir imagenes",
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                    }
                }
                LazyColumn {
                    items(selectedImageUris) { uri ->
                        uri?.let {
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxSize(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2)),
                                onClick = {
                                    // accion al presionar la imagen
                                }
                            ) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    AsyncImage(
                                        model = uri,
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxWidth(),
                                        contentScale = ContentScale.Crop
                                    )
                                    IconButton(
                                        onClick = {
                                            // alert dialog para preguntar si quiere eliminar
                                            selectedImageUris.remove(uri)
                                        },
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .size(20.dp),// Align button at top end
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "eliminar imagen",
                                            modifier = Modifier.size(20.dp),
                                            tint = Color.Black
                                        )
                                    }

                                }

                            }

                        }
                    }
                }
            }
        }
    )
}

@Composable
fun Filtros(onDismiss: () -> Unit) {

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Aceptar")
            }
        },
        /*dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        },*/
        text = {
            Column {
                TextField(value = "", onValueChange = {  }, label = { Text("Nombre") })
                TextField(value = "", onValueChange = {  }, label = { Text("Fecha") })
                TextField(value = "", onValueChange = {  }, label = { Text("Responsable") })
                TextField(value = "", onValueChange = {  }, label = { Text("Curso") })
            }
        }
    )
}

// Calendario de actividades

@Composable
fun ActivityCalendarApp(
    navController: NavController,
    actividades: List<Actividad>,
    actividadViewModel: ActividadViewModel,
    profParticipanteViewModel: ProfParticipanteViewModel,
    grupoParticipanteViewModel: GrupoParticipanteViewModel
) {
    // Estado para las actividades (con título y horario)
    var activities by remember { mutableStateOf(mapOf<LocalDate, Actividad>()) }

    for (actividad in actividades) {
        activities = activities + (LocalDate.parse(actividad.fini) to actividad)
    }

    // Estado del calendario
    val calendarState = rememberCalendarState()

    // Estado del día seleccionado
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp)) {
        // Mostrar el calendario
        Box(modifier = Modifier
            .background(Color(0xFFD0E8F2), shape = RoundedCornerShape(12.dp))
            .padding(5.dp)){
            Calendar(
                calendarState = calendarState,
                showAdjacentMonths = true,
                firstDayOfWeek = java.time.DayOfWeek.MONDAY,
                dayContent = { dayState ->
                    MyDayContentWithActivities(
                        dayState,
                        activities,
                        onClick = {
                            selectedDate = dayState.date // Al hacer click en un día, se selecciona el día
                        }
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Mostrar información sobre el día seleccionado
        selectedDate?.let { date ->
            ActivityDetails(
                navController = navController,
                date = date,
                activity = activities[date],
                actividadViewModel = actividadViewModel,
                profParticipanteViewModel = profParticipanteViewModel,
                grupoParticipanteViewModel = grupoParticipanteViewModel
            )
        }
    }
}

@Composable
fun MyDayContentWithActivities(
    dayState: DayState<*>,
    activities: Map<LocalDate, Actividad>,
    onClick: () -> Unit
) {
    val hasActivity = activities.containsKey(dayState.date)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = onClick)
    ) {
        if (hasActivity) {
            Box(
                modifier = Modifier
                    .size(17.dp)
                    .background(Color.White, shape = CircleShape)
                    .align(Alignment.Center)

            )
        }
        Text(
            text = dayState.date.dayOfMonth.toString(),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )

        // Si tiene actividad, mostrar un punto debajo del número

    }
}

@Composable
fun ActivityDetails(
    navController: NavController,
    date: LocalDate,
    activity: Actividad?,
    actividadViewModel: ActividadViewModel,
    profParticipanteViewModel: ProfParticipanteViewModel,
    grupoParticipanteViewModel: GrupoParticipanteViewModel
) {
    Card (modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()
        .then(
            if (activity != null) Modifier.clickable {
                actividadViewModel.getActividadById(activity.id)
                profParticipanteViewModel.getProfesoresParticipantes()
                grupoParticipanteViewModel.getGruposParticipantes()
                navController.navigate("details")
            } else Modifier
        ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2)),
        ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
            contentAlignment = Alignment.Center) {
            Column (modifier = Modifier
                .fillMaxSize()) {
                activity?.let {
                    Text(text = "Fecha: ${date.dayOfMonth}-${date.monthValue}-${date.year}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Título: ${it.titulo}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    //Text(text = "Horario: ${it.time}")
                } ?: run {
                    Text(text = "Fecha: ${date.dayOfMonth}-${date.monthValue}-${date.year}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Actividad: No hay actividad programada", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }

}

// mapa

@Composable
fun MapScreen() {
    val context = LocalContext.current

    val location = LatLng(43.35257675380246, -4.062506714329061) // Cambia a la ubicación deseada
    val cameraPositionState = rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(location, 10f) }

    GoogleMap(
        cameraPositionState = cameraPositionState
    ) {





    }

    /*val puntosDeInteres = listOf(
        GeoPoint(43.35257675380246, -4.062506714329061), // Coordenadas 1
        GeoPoint(43.3530000, -4.0610000), // Coordenadas 2
        GeoPoint(43.3500000, -4.0650000)
    )*/

    /*AndroidView(
        factory = {
            MapView(context).apply {
                setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK)
                controller.setCenter(GeoPoint(43.35257675380246, -4.062506714329061)) // Coordenadas de inicio
                controller.setZoom(18) // Nivel de zoom

                // Añadir los marcadores con títulos secuenciales
                puntosDeInteres.forEachIndexed { index, geoPoint ->
                    val title = "Punto de Interés ${index + 1}"  // Título secuencial
                    addMarker(geoPoint, title)
                }

                val polyline = Polyline()
                puntosDeInteres.forEach { geoPoint ->
                    polyline.addPoint(geoPoint)  // Añadir puntos al Polyline
                }

                // Hacer la línea discontinua
                val dashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f) // 10px línea y 5px espacio
                polyline.outlinePaint.pathEffect = dashEffect
                overlays.add(polyline)  // Añadir la línea al mapa
            }
        }
        modifier = Modifier.fillMaxSize() // El mapa se ajusta al tamaño de su contenedor
    )*/
}
/*
// Función para añadir un marcador en el mapa
fun MapView.addMarker(location: GeoPoint, title: String) {
    val marker = Marker(this)
    marker.position = location
    marker.title = title
    overlays.add(marker)
}*/