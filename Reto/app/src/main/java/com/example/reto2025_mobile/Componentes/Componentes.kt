package com.example.reto2025_mobile.Componentes

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.reto2025_mobile.Componentes.Usuario.activo
import com.example.reto2025_mobile.Componentes.Usuario.apellidos
import com.example.reto2025_mobile.Componentes.Usuario.correo
import com.example.reto2025_mobile.Componentes.Usuario.depart
import com.example.reto2025_mobile.Componentes.Usuario.dni
import com.example.reto2025_mobile.Componentes.Usuario.esJefeDep
import com.example.reto2025_mobile.Componentes.Usuario.nombre
import com.example.reto2025_mobile.Componentes.Usuario.password
import com.example.reto2025_mobile.Componentes.Usuario.rol
import com.example.reto2025_mobile.Componentes.Usuario.urlFoto
import com.example.reto2025_mobile.Componentes.Usuario.uuid
import com.example.reto2025_mobile.Navigation.ItemsNav
import com.example.reto2025_mobile.R
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ViewModel.GrupoParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfParticipanteViewModel
import com.example.reto2025_mobile.data.Actividad
import com.example.reto2025_mobile.data.Departamento
import com.example.reto2025_mobile.data.ProfParticipante
import com.example.reto2025_mobile.ui.theme.GreenBar
import com.example.reto2025_mobile.ui.theme.BlueContainer
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import io.github.boguszpawlowski.composecalendar.Calendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.Normalizer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

//Top bar de la pantalla de Detalles de una actividad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    navController: NavController
) {
    TopAppBar(
        title = {
            Icon(
                painter = painterResource(R.drawable.logowhite), // Asegúrate de tener un logo blanco en res/drawable
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(8.dp)
                    .size(250.dp), // Ajusta el tamaño según sea necesario
                tint = Color.Unspecified // Asegúrate de que el color no se sobreescriba
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = GreenBar,
            titleContentColor = Color.White
        )
    )
}

//Top bar de la pantalla de Actividades

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActividadesTopAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Icon(
                painter = painterResource(R.drawable.logowhite), // Asegúrate de tener un logo blanco en res/drawable
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(8.dp)
                    .size(250.dp), // Ajusta el tamaño según sea necesario
                tint = Color.Unspecified // Asegúrate de que el color no se sobreescriba
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = GreenBar,
            titleContentColor = Color.White
        ),
        actions = {
            Box {
                Row {
                    IconButton(onClick = { navController.navigate("FAQ") }) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "FaQ"
                        )
                    }
                }
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilTopAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Icon(
                painter = painterResource(R.drawable.logowhite), // Asegúrate de tener un logo blanco en res/drawable
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(8.dp)
                    .size(250.dp), // Ajusta el tamaño según sea necesario
                tint = Color.Unspecified // Asegúrate de que el color no se sobreescriba
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = GreenBar,
            titleContentColor = Color.White
        )
    )
}

//Top bar de la pantalla de Inicio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(navController: NavController) {
    var showlogout by remember { mutableStateOf(false) }
    val context = LocalContext.current
    TopAppBar(
        title = {
        },
        navigationIcon = {
            Icon(
                painter = painterResource(R.drawable.logowhite), // Asegúrate de tener un logo blanco en res/drawable
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(8.dp)
                    .size(250.dp), // Ajusta el tamaño según sea necesario
                tint = Color.Unspecified // Asegúrate de que el color no se sobreescriba
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = GreenBar,
            titleContentColor = Color.White
        ),
        actions = {
            Box {
                IconButton(onClick = { showlogout = true }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.logout),
                        contentDescription = "cerrar sesion"
                    )
                    if (showlogout) {
                        AlertDialog(
                            onDismissRequest = { },
                            confirmButton = {
                                Button(onClick = {
                                    clearLoginData(context)
                                    navController.navigate("loggin")
                                    showlogout = false
                                })
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController) {
    var showlogout by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Icon(
                painter = painterResource(R.drawable.logowhite), // Asegúrate de tener un logo blanco en res/drawable
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(8.dp)
                    .size(250.dp), // Ajusta el tamaño según sea necesario
                tint = Color.Unspecified // Asegúrate de que el color no se sobreescriba
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = GreenBar,
            titleContentColor = Color.White
        )
    )
}

// Bottom Bar con navegacion entre pantallas

@Composable
fun currentRoute(navController: NavController): String? =
    navController.currentBackStackEntryAsState().value?.destination?.route

@Composable
fun BottomAppBar(navController: NavController) {
    val bar_items = listOf(
        ItemsNav.Item_bottom_nav_acts,
        ItemsNav.Item_bottom_nav_misActividades,
        ItemsNav.Item_bottom_nav_home,
        ItemsNav.Item_bottom_nav_calendar,
        ItemsNav.Item_bottom_nav_perfil,

    )
    NavigationBar(
        containerColor = GreenBar,
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

@Composable
fun BottomDetailBar(actividad: Actividad, profParticipantes: List<ProfParticipante>) {
    Row(
        modifier = Modifier
            .background(GreenBar),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        var showMap by remember { mutableStateOf(false) }
        var showPhoto by remember { mutableStateOf(false) }
        var enabledAddPhoto by remember { mutableStateOf(false) }

        for (prof in profParticipantes) {
            if (prof.actividad.id == actividad.id && prof.profesor.uuid == Usuario.uuid) {
                enabledAddPhoto = true
            }
        }

        Row {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.5f),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF9ACB63)),
                onClick = { showPhoto = true },
                enabled = enabledAddPhoto

            ) {
                if (showPhoto) Fotos(onDismiss = { showPhoto = false })
                Row {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.addphoto),
                        contentDescription = "photo",
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Añadir imagenes",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.5f),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF9ACB63)),
                onClick = { showMap = true }

            ) {
                if (showMap) Mapa(onDismiss = { showMap = false })
                Row {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = "Ubicacion",
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Ubicacion",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

    }
}

// Cuadros de dialogo para filtrar actividades y añadir incidencias


@Composable
fun Mapa(onDismiss: () -> Unit) {
    /*Dialog( onDismissRequest = onDismiss ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            MapScreen()
        }
    }*/
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
fun Pics(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .background(Color.Transparent)
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = ImageVector.vectorResource(R.drawable.photo),
                contentDescription = "foto"
            )
            /*AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )*/
        }
    }
}

@Composable
fun Fotos(onDismiss: () -> Unit) {
    val context = LocalContext.current
    var showOptions by remember { mutableStateOf(false) }
    val selectedImageUris = remember { mutableStateListOf<Uri?>() }
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            uris.forEach { uri ->
                uri?.let { selectedImageUris.add(it) }
            }
        }
    )
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success: Boolean ->
            if (success) {
                // Guardar la imagen en la galería
                val contentValues = ContentValues().apply {
                    put(
                        MediaStore.Images.Media.DISPLAY_NAME,
                        "IMG_${System.currentTimeMillis()}.jpg"
                    )
                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                    put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val resolver = context.contentResolver
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                    ?.let { galleryUri ->
                        resolver.openOutputStream(galleryUri)?.use { outputStream ->
                            photoUri?.let {
                                resolver.openInputStream(photoUri!!)?.use { inputStream ->
                                    inputStream.copyTo(outputStream)
                                }
                            }
                        }
                    }
                // Añadir la URI de la imagen a las imágenes seleccionadas
                selectedImageUris.add(photoUri)
            }
        }
    )


    AlertDialog(
        modifier = Modifier
            .fillMaxWidth(),
        onDismissRequest = onDismiss,
        confirmButton = {
        },
        text = {
            Column {
                if (selectedImageUris.isNotEmpty()) {
                    Box(modifier = Modifier.weight(0.8f)) {
                        LazyColumn {
                            items(selectedImageUris) { uri ->
                                uri?.let {
                                    Card(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .fillMaxSize(),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = BlueContainer),
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
                        colors = CardDefaults.cardColors(containerColor = BlueContainer),
                        onClick = {
                            // asi se cierra la camara
                            var tempPhotoUri = FileProvider.getUriForFile(
                                context,
                                "${context.packageName}.provider",
                                File(context.cacheDir, "temp_image.jpg")
                            )
                            photoUri = tempPhotoUri
                            cameraLauncher.launch(tempPhotoUri)
                        }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.addphoto),
                                contentDescription = "añadir desde camara",
                                modifier = Modifier.size(32.dp)
                            )

                        }
                    }

                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(0.5f),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = BlueContainer),
                        onClick = {
                            multiplePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                            showOptions = false
                        }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.image_search),
                                contentDescription = "añadir desde galeria",
                                modifier = Modifier.size(32.dp)
                            )

                        }
                    }

                    if (selectedImageUris.isNotEmpty()) {
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(0.5f),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = BlueContainer),
                            onClick = {
                                // subir imagenes
                            }
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.save),
                                    contentDescription = "subir imagenes",
                                    modifier = Modifier.size(32.dp)
                                )

                            }
                        }
                    }
                }

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
    var activities by remember { mutableStateOf(mapOf<LocalDate, List<Actividad>>()) }


    LaunchedEffect(actividades) {
        val updatedActivities = actividades.groupBy { LocalDate.parse(it.fini) }
        activities = updatedActivities
    }
    /*for (actividad in actividades) {
        val date = LocalDate.parse(actividad.fini)
        activities = activities + (date to (activities[date]?.plus(actividad) ?: listOf(actividad)))
    }*/

    // Estado del calendario
    val calendarState = rememberCalendarState()

    // Estado del día seleccionado
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    var showCard by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {

        // Mostrar el calendario
        Box(
            modifier = Modifier
                .background(BlueContainer, shape = RoundedCornerShape(12.dp))
                .padding(5.dp)
        ) {
            Calendar(
                calendarState = calendarState,
                showAdjacentMonths = true,
                firstDayOfWeek = java.time.DayOfWeek.MONDAY,
                dayContent = { dayState ->
                    MyDayContentWithActivities(
                        dayState,
                        activities,
                        onClick = {
                            selectedDate =
                                dayState.date // Al hacer click en un día, se selecciona el día
                        }
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
            // Mostrar información sobre el día seleccionado
            selectedDate?.let { date ->
                showCard = false
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

        if (showCard) {
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = BlueContainer),
            ) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Selecciona un día para ver las actividades",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    }
}

@Composable
fun MyDayContentWithActivities(
    dayState: DayState<*>,
    activities: Map<LocalDate, List<Actividad>>,
    onClick: () -> Unit
) {
    val hasActivity = activities.containsKey(dayState.date)
    val today = LocalDate.now()
    val isToday = dayState.date == today

    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(30.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (isToday) {
            if (hasActivity) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .background(Color.White, shape = CircleShape)

                )
            } else {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .background(Color.Transparent, shape = CircleShape)
                        .border(0.5.dp, Color.Black, shape = CircleShape)

                )
            }
        } else {
            if (hasActivity) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .background(Color.White, shape = CircleShape)

                )
            }
        }

        Text(
            text = dayState.date.dayOfMonth.toString(),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = if (isToday) Color.Red else Color.Black,
            modifier = Modifier.align(Alignment.Center)

        )
    }
}

@Composable
fun ActivityDetails(
    navController: NavController,
    date: LocalDate,
    activity: List<Actividad>?,
    actividadViewModel: ActividadViewModel,
    profParticipanteViewModel: ProfParticipanteViewModel,
    grupoParticipanteViewModel: GrupoParticipanteViewModel
) {

    if (activity != null) {
        activity.forEach { activity ->
            actividadViewModel.getActividadById(activity.id)
            profParticipanteViewModel.getProfesoresParticipantes()
            grupoParticipanteViewModel.getGruposParticipantes()


            val color = SelectColor(activity.estado)

            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = color),
                onClick = { navController.navigate("details") }
            ) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column (modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Fecha: ${date.dayOfMonth}-${date.monthValue}-${date.year}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Título: ${activity.titulo}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
        }
    }else{

        Card(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = BlueContainer),
            onClick = { navController.navigate("details") }
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column (modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Fecha: ${date.dayOfMonth}-${date.monthValue}-${date.year}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Actividad: No hay actividad programada",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }

    /*Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .then(
                if (activity != null) Modifier.clickable {
                    activity.forEach { activity ->
                        actividadViewModel.getActividadById(activity.id)
                    }
                    profParticipanteViewModel.getProfesoresParticipantes()
                    grupoParticipanteViewModel.getGruposParticipantes()
                    navController.navigate("details")
                } else Modifier
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = BlueContainer),
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                activity?.forEach() { activity ->
                    Text(
                        text = "Fecha: ${date.dayOfMonth}-${date.monthValue}-${date.year}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Título: ${activity.titulo}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    //Text(text = "Horario: ${it.time}")
                } ?: run {
                    Text(
                        text = "Fecha: ${date.dayOfMonth}-${date.monthValue}-${date.year}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Actividad: No hay actividad programada",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }*/

}

// mapa

@Composable
fun MapScreen() {
    val context = LocalContext.current

    val location = LatLng(43.35257675380246, -4.062506714329061) // Cambia a la ubicación deseada
    val cameraPositionState =
        rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(location, 10f) }

    GoogleMap(
        cameraPositionState = cameraPositionState
    ) {

    }

}

fun formatFecha(fecha: String): String {
    val date = LocalDate.parse(fecha)
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    return date.format(formatter)
}

fun normalizeString(input: String): String {
    return Normalizer.normalize(input, Normalizer.Form.NFD)
        .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")
        .lowercase()
}

fun SelectColor(estado: String): Color {
    var color = Color(0xFFD0E8F2)
    if (estado == "SOLICITADA") {
        color = Color(0xFFD0E8F2)
    } else if (estado == "DENEGADA") {
        color = Color(0xFFCD5C5C)
    } else if (estado == "APROBADA") {
        color = Color(0xFFADD8E6)
    } else if (estado == "REALIZADA") {
        color = Color(0xFF90EE90)
    } else if (estado == "REALIZANDOSE") {
        color = Color(0xFFFFFFE0)
    } else if (estado == "CANCELADA") {
        color = Color(0xFFD3D3D3)
    }
    return color
}


fun saveLoginData(context: Context, email: String, password: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString("email", email)
    editor.putString("password", password)
    editor.apply()
}

fun getLoginData(context: Context): Pair<String?, String?> {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
    val email = sharedPreferences.getString("email", null)
    val password = sharedPreferences.getString("password", null)
    return Pair(email, password)
}

fun readLogData(context: Context): List<Usuario> {
    val fileName = "user_data.csv"
    val file = File(context.filesDir, fileName)
    val users = mutableListOf<Usuario>()

    if (file.exists()) {
        FileInputStream(file).use { input ->
            input.bufferedReader().useLines { lines ->
                lines.drop(1).forEach { line -> // Skip the header
                    val parts = line.split(",")
                    if (parts.size == 11) {
                        val user = Usuario.apply {
                            uuid = parts[0]
                            dni = parts[1]
                            nombre = parts[2]
                            apellidos = parts[3]
                            correo = parts[4]
                            password = parts[5]
                            rol = parts[6]
                            activo = parts[7].toBoolean()
                            urlFoto = parts[8]
                            esJefeDep = parts[9].toBoolean()
                        }
                        users.add(user)
                    }
                }
            }
        }
    }
    return users
}

fun clearLoginData(context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.clear()
    editor.apply()
}
