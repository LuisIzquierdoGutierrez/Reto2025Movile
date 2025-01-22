package com.example.reto2025_mobile.Componentes

import android.net.Uri
<<<<<<< Updated upstream
=======
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
>>>>>>> Stashed changes
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.reto2025_mobile.Navigation.ItemsNav
import com.example.reto2025_mobile.R
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ViewModel.GrupoParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfParticipanteViewModel
import com.example.reto2025_mobile.data.Actividad
import com.example.reto2025_mobile.data.Profesor
import com.example.reto2025_mobile.ui.theme.GreenBar
<<<<<<< Updated upstream
import com.example.reto2025_mobile.ui.theme.GreenContainer
=======
import com.example.reto2025_mobile.ui.theme.BlueContainer
>>>>>>> Stashed changes
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import io.github.boguszpawlowski.composecalendar.Calendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    navController: NavController
) {
<<<<<<< Updated upstream
    var showIncidencia by remember { mutableStateOf(false) }

=======
>>>>>>> Stashed changes
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
            IconButton(onClick = {navController.popBackStack()}) {
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
    var expanded by remember { mutableStateOf(false) }
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
            IconButton(onClick = {navController.popBackStack()}) {
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
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.filter),
                            contentDescription = "Filtros"
                        )
<<<<<<< Updated upstream
                        if(expanded){
=======
                        if (expanded) {
>>>>>>> Stashed changes
                            Filtros(onDismiss = { expanded = false })
                        }
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
            IconButton(onClick = {navController.popBackStack()}) {
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
<<<<<<< Updated upstream
            IconButton(onClick = {navController.popBackStack()}) {
=======
            IconButton(onClick = { navController.popBackStack() }) {
>>>>>>> Stashed changes
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
fun currentRoute(navController: NavController) :String? =
    navController.currentBackStackEntryAsState().value?.destination?.route

@Composable
fun BottomAppBar(navController: NavController) {
    val bar_items = listOf(
        ItemsNav.Item_bottom_nav_acts,
        ItemsNav.Item_bottom_nav_calendar,
        ItemsNav.Item_bottom_nav_home,
        ItemsNav.Item_bottom_nav_perfil,
        ItemsNav.Item_bottom_nav_faq,
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
<<<<<<< Updated upstream
=======
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
/*
@Composable
fun Pic(onDismiss: () -> Unit) {
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .size(400.dp),
        onDismissRequest = onDismiss,
        confirmButton = {},
        text = {
            Box(modifier = Modifier.fillMaxSize()) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = ImageVector.vectorResource(R.drawable.photo),
                    contentDescription = "foto"
                )
            }
        }
    )
}*/

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
                    put(MediaStore.Images.Media.DISPLAY_NAME, "IMG_${System.currentTimeMillis()}.jpg")
                    put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                    put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val resolver = context.contentResolver
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)?.let { galleryUri ->
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

@Composable
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        Box(modifier = Modifier
            .background(GreenContainer, shape = RoundedCornerShape(12.dp))
            .padding(5.dp)){
=======
        Box(
            modifier = Modifier
                .background(BlueContainer, shape = RoundedCornerShape(12.dp))
                .padding(5.dp)
        ) {
>>>>>>> Stashed changes
            Calendar(
                calendarState = calendarState,
                showAdjacentMonths = true,
                firstDayOfWeek = java.time.DayOfWeek.MONDAY,
                dayContent = { dayState ->
                    MyDayContentWithActivities(
                        dayState,
                        activities,
                        onClick = {
<<<<<<< Updated upstream
                            selectedDate = dayState.date // Al hacer click en un día, se selecciona el día
=======
                            selectedDate =
                                dayState.date // Al hacer click en un día, se selecciona el día
>>>>>>> Stashed changes
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
    val today = LocalDate.now()
    val isToday = dayState.date == today

    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = onClick)
    ) {
        if(isToday){
            if (hasActivity) {
                Box(
                    modifier = Modifier
                        .size(17.dp)
                        .background(Color.White, shape = CircleShape)
                        .align(Alignment.Center)

                )
<<<<<<< Updated upstream
            }else{
=======
            } else {
>>>>>>> Stashed changes
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .background(Color.Transparent, shape = CircleShape)
                        .border(0.5.dp, Color.Black, shape = CircleShape)
<<<<<<< Updated upstream
                        .align(Alignment.Center)
=======
>>>>>>> Stashed changes

                )
            }
        } else {
            if (hasActivity) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .background(Color.White, shape = CircleShape)
<<<<<<< Updated upstream
                        .align(Alignment.Center)
=======
>>>>>>> Stashed changes

                )
            }
        }

        Text(
            text = dayState.date.dayOfMonth.toString(),
            style = MaterialTheme.typography.bodyMedium,
<<<<<<< Updated upstream
            textAlign = TextAlign.Right,
            color = if (isToday) Color.Red else Color.Black // Cambia el color del texto si es el día de hoy

        )

        // Si tiene actividad, mostrar un punto debajo del número

=======
            textAlign = TextAlign.Center,
            color = if (isToday) Color.Red else Color.Black,
            modifier = Modifier.align(Alignment.Center)

        )
>>>>>>> Stashed changes
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
        colors = CardDefaults.cardColors(containerColor = GreenContainer),
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
<<<<<<< Updated upstream
                    Text(text = "Actividad: No hay actividad programada", fontSize = 18.sp, fontWeight = FontWeight.Bold)
=======
                    Text(
                        text = "Actividad: No hay actividad programada",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    val cameraPositionState = rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(location, 10f) }
=======
    val cameraPositionState =
        rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(location, 10f) }
>>>>>>> Stashed changes

    GoogleMap(
        cameraPositionState = cameraPositionState
    ) {

    }

}
