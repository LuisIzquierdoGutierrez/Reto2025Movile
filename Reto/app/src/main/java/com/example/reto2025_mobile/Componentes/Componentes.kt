package com.example.reto2025_mobile.Componentes

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.reto2025_mobile.Navigation.ItemsNav
import com.example.reto2025_mobile.R
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ViewModel.GrupoParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfParticipanteViewModel
import com.example.reto2025_mobile.data.Actividad
import com.example.reto2025_mobile.data.ProfParticipante
import com.example.reto2025_mobile.ui.theme.GreenBar
import com.example.reto2025_mobile.ui.theme.BlueContainer
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import io.github.boguszpawlowski.composecalendar.Calendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import java.io.File
import java.io.FileInputStream
import java.text.Normalizer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.util.Log
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.material3.*
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import com.example.reto2025_mobile.ViewModel.FotoViewModel
import com.example.reto2025_mobile.ViewModel.PuntosInteresViewModel
import com.example.reto2025_mobile.data.PuntoInteres
import com.google.android.gms.maps.model.Marker
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.FileOutputStream
import java.io.InputStream

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
fun BottomDetailBar(
    actividad: Actividad,
    profParticipantes: List<ProfParticipante>,
    puntosInteresViewModel: PuntosInteresViewModel,
    participantes: MutableSet<String>
) {
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
                if (showPhoto) actividad.id?.let { Fotos(onDismiss = { showPhoto = false }, idActividad = it, fotoViewModel = FotoViewModel()) }
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
                if (showMap) Mapa(onDismiss = { showMap = false }, puntosInteresViewModel = puntosInteresViewModel, actividad = actividad, participantes = participantes)
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
fun Mapa(
    onDismiss: () -> Unit,
    puntosInteresViewModel: PuntosInteresViewModel,
    actividad: Actividad,
    participantes: MutableSet<String>
) {
    Dialog(onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .background(Color.White) // Fondo blanco para el diálogo
        ) {
            MapScreen(puntosInteresViewModel = puntosInteresViewModel, actividad = actividad, participantes = participantes)
        }
    }
    /*AlertDialog(
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
    )*/
}

@Composable
fun Pics(bitmap: Bitmap?, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Foto de actividad",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(400.dp)
            )
        }
    }

}

fun prepareFilePart(context: Context, uri: Uri, description: String): MultipartBody.Part {
    val file = File(uri.path)
    val requestFile = RequestBody.create(context.contentResolver.getType(uri)?.toMediaTypeOrNull(), file)
    return MultipartBody.Part.createFormData("file", file.name, requestFile)
}

fun createPartFromString(description: String): RequestBody {
    return RequestBody.create("text/plain".toMediaTypeOrNull(), description)
}

@Composable
fun Fotos(onDismiss: () -> Unit, idActividad: Int, fotoViewModel: FotoViewModel) {
    val context = LocalContext.current
    val selectedImageUris: SnapshotStateList<Uri?> = remember { mutableStateListOf<Uri?>() }

    //var selectedImageUris = remember { mutableStateListOf<Uri?>() }
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->
            uris.forEach { uri ->
                uri?.let { selectedImageUris.add(it) }
            }
        }
    )
    var isCameraVisible by remember { mutableStateOf(false) }


    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
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
                            isCameraVisible = true
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
                                val photoDir = File(context.filesDir, "fotos")
                                if (!photoDir.exists()) {
                                    photoDir.mkdir()
                                }
                                //val photoFile = File(photoDir, "photo_${System.currentTimeMillis()}.jpg")
                                selectedImageUris.forEach { uri ->
                                    uri?.let {
                                        val foto = getFileFromUri(context, uri)

                                        val rotatedBitmap =
                                            foto?.let { it1 -> rotateImageIfRequired(context, it1) }
                                        val outputStream = FileOutputStream(foto)
                                        if (rotatedBitmap != null) {
                                            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                                        }
                                        outputStream.close()

                                        val savedUri = Uri.fromFile(foto)
                                        // Subir la foto a la API
                                        val descripcion = "Foto de la actividad ${idActividad}"
                                        fotoViewModel.uploadPhoto(context, idActividad, descripcion, savedUri).observeForever { result ->
                                            if (result.isSuccess) {
                                                fotoViewModel.fetchFotos(idActividad)
                                                Toast.makeText(context, "Foto guardada correctamente", Toast.LENGTH_SHORT).show()
                                            } else {

                                                Toast.makeText(context, "Error al guardar foto", Toast.LENGTH_SHORT).show()
                                                //Log.d("pruebasubida","Error al subir la foto")
                                            }
                                        }

                                    }
                                }
                                onDismiss()
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
    if (isCameraVisible) {

        var showDialog by remember { mutableStateOf(false) }
        var photoUri by remember { mutableStateOf<Uri?>(null) }

        // Solicitar permisos
        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { /*permissions ->
            if (permissions[Manifest.permission.CAMERA] == true &&
                permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true &&
                permissions[Manifest.permission.READ_EXTERNAL_STORAGE] == true
            ) {
                // Permisos concedidos, puedes iniciar la cámara
                Toast.makeText(context, "Permisos concedidos", Toast.LENGTH_SHORT).show()
            } else {
                // Permisos denegados
                Toast.makeText(context, "Permisos denegados", Toast.LENGTH_SHORT).show()
            }*/
            }
        )

        LaunchedEffect(key1 = true) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            )
        }

        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        val imageCapture = ImageCapture.Builder().build()
        val preview = Preview.Builder().build()
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        Dialog(onDismissRequest = { isCameraVisible = false },
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // Vista previa de la cámara ocupa toda la pantalla
                AndroidView(
                    factory = { context ->
                        val previewView = PreviewView(context).apply {
                            preview.setSurfaceProvider(surfaceProvider)
                        }
                        previewView
                    },
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(100.dp), // Padding alrededor del botón
                    contentAlignment = Alignment.BottomCenter
                ) {
                    IconButton(onClick = {
                        showDialog = true
                        val photoDir = File(context.filesDir, "fotos")
                        if (!photoDir.exists()) {
                            photoDir.mkdir()
                        }
                        val photoFile = File(photoDir, "photo_${System.currentTimeMillis()}.jpg")

                        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

                        // Tomar la foto y guardarla en el archivo creado
                        imageCapture.takePicture(
                            outputOptions,
                            ContextCompat.getMainExecutor(context),
                            object : ImageCapture.OnImageSavedCallback {
                                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                    val savedUri = Uri.fromFile(photoFile)

                                    selectedImageUris.add(savedUri)
                                }

                                override fun onError(exception: ImageCaptureException) {
                                    //Toast.makeText(context, "Error al subir la foto: ${exception.message}", Toast.LENGTH_SHORT).show()
                                    //onUploadError(exception)
                                }
                            }
                        )
                        showDialog = true
                        isCameraVisible = false
                        /*takePhotoAndUpload(
                            imageCapture = imageCapture,
                            context = context,
                            idActividad = idActividad,
                            fotoViewModel = fotoViewModel,
                            onUploadSuccess = { uri ->
                                Toast.makeText(context, "Foto subida con éxito: $uri", Toast.LENGTH_LONG).show()
                            },
                            onUploadError = { exception ->
                                Toast.makeText(context, "Error al subir la foto: ${exception.message}", Toast.LENGTH_SHORT).show()
                            }
                        )*/
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.photo),
                            contentDescription = "Tomar foto",
                            modifier = Modifier.size(100.dp)
                        )
                    }

                }
            }
            //probando camara

            // Mostrar el diálogo de confirmación con la foto
           /* if (showDialog && photoUri != null) {
                AlertDialog(
                    onDismissRequest = { showDialog = false
                                       isCameraVisible = true},
                    properties = DialogProperties(usePlatformDefaultWidth = false), modifier = Modifier.height(600.dp),
                    title = { Text("Añadir Foto") },
                    text = {
                        Column(modifier = Modifier.fillMaxSize()) {
                            // Mostrar la foto tomada
                            Box(modifier = Modifier.fillMaxSize()) {
                                photoUri?.let {
                                    Image(
                                        painter = rememberImagePainter(it),
                                        contentDescription = "Foto tomada",
                                        modifier = Modifier
                                            .fillMaxSize()
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("¿Añadir foto?")
                        }
                    },
                    containerColor = Color.LightGray,
                    confirmButton = {
                        TextButton(
                            onClick = {
                                photoUri?.let { uri ->
                                    selectedImageUris.add(uri)
                                }
                                isCameraVisible = false
                                showDialog = false
                            }
                        ) {
                            Text("Añadir")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showDialog = false
                                photoUri = null // Reset photoUri to allow retaking the photo
                            }
                        ) {
                            Text("Repetir")
                        }
                    }
                )
            }*/

            // Usamos LaunchedEffect para inicializar la cámara una vez que el composable se muestra
            LaunchedEffect(key1 = true) {
                try {
                    // Espera a que el proveedor de cámara esté listo
                    val cameraProvider = cameraProviderFuture.get()

                    // Asegúrate de que la cámara esté vinculada correctamente
                    cameraProvider.unbindAll()

                    // Configura la cámara con la vista previa y las capturas de imagen
                    cameraProvider.bindToLifecycle(
                        context as ComponentActivity, cameraSelector, preview, imageCapture
                    )
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Error al configurar la cámara: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

fun rotateImageIfRequired(context: Context, photoFile: File): Bitmap {
    val bitmap = BitmapFactory.decodeFile(photoFile.path)
    val exif = ExifInterface(photoFile.path)
    val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

    return when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90f)
        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180f)
        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270f)
        else -> bitmap
    }
}

fun rotateImage(img: Bitmap, degree: Float): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(degree)
    return Bitmap.createBitmap(img, 0, 0, img.width, img.height, matrix, true)
}

fun getFileFromUri(context: Context, uri: Uri): File? {
    return try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val tempFile = File(context.cacheDir, "temp_image_${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(tempFile)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()
        return tempFile
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
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
            colors = CardDefaults.cardColors(containerColor = BlueContainer)
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



}

// mapa

@Composable
fun MapScreen(
    puntosInteresViewModel: PuntosInteresViewModel,
    actividad: Actividad,
    participantes: MutableSet<String>
) {
    val porDefecto = LatLng(43.35257675380246, -4.062506714329061)// Ies Miguel Herrero
    var localizacion: LatLng = porDefecto

    if(actividad.latitud != null && actividad.longitud != null){
        localizacion = LatLng(actividad.latitud.toDouble(), actividad.longitud.toDouble())
    }
    val cameraPositionState = rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(localizacion, 17f) }

    val puntosInteres: List<PuntoInteres> by puntosInteresViewModel.puntosInteres.observeAsState( emptyList() )
    //var markers by remember { mutableStateOf(listOf<Pair<LatLng, String>>()) }

    var markerToDelete by remember { mutableStateOf<Pair<LatLng, String>?>(null) }

    var newMarkerPosition by remember { mutableStateOf<LatLng?>(null) }
    var description by remember { mutableStateOf("") }

    var show by remember { mutableStateOf(false) }
    var enable by remember { mutableStateOf(false) }

    for(prof in participantes){
        if(prof == Usuario.uuid){
            enable = true
        }
    }



    GoogleMap(
        cameraPositionState = cameraPositionState,
        onMapLongClick = {
            if(enable){
                description = ""
                newMarkerPosition = it
                show = true
            }

        }
    ) {
        puntosInteres.forEach{ pto ->
            val pos = LatLng(pto.latitud.toDouble(), pto.longitud.toDouble())
            Marker(
                state = MarkerState(position = pos),
                title = actividad.titulo,
                snippet = pto.descripcion,
                onInfoWindowLongClick = {
                    if(enable){
                        markerToDelete = Pair(pos, pto.descripcion)
                    }
                }
            )
        }
    }
    markerToDelete?.let { (position, description) ->
        Dialog(
            onDismissRequest = { markerToDelete = null },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("¿Quieres eliminar el punto de interés?")
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Button(onClick = {
                            puntosInteres.forEach() { puntoInteres ->
                                val marker = LatLng(puntoInteres.latitud.toDouble(), puntoInteres.longitud.toDouble())
                                if (marker == position) {
                                    val puntoInteres = PuntoInteres(
                                        id = puntoInteres.id,
                                        descripcion = description,
                                        latitud = puntoInteres.latitud,
                                        longitud = puntoInteres.longitud,
                                        actividad = actividad
                                    )
                                    puntosInteresViewModel.deletePuntoInteres(puntoInteres)
                                }
                            }
                            markerToDelete = null
                        }) {
                            Text("Sí")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(onClick = { markerToDelete = null }) {
                            Text("No")
                        }
                    }
                }
            }
        }
    }
    if(show){
        AlertDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    val puntoInteres = PuntoInteres(
                        id = null,
                        descripcion = description,
                        latitud = newMarkerPosition!!.latitude.toString(),
                        longitud = newMarkerPosition!!.longitude.toString(),
                        actividad = actividad
                    )
                    puntosInteresViewModel.savePuntoInteres(puntoInteres)

                    show = false
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                Button(onClick = {show = false }) {
                    Text("Cancelar")
                }
            },
            title = {
                Text("¿Añadir punto de interes?")
            },
            text = {
                Column {
                    Text("Descripcion:")
                    TextField(value = description, onValueChange = { description = it }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),)
                }
            }
        )
    }
}

fun pintaptos(puntosInteres: List<PuntoInteres>): List<Pair<LatLng, String>> {
    val markers = mutableListOf<Pair<LatLng, String>>()
    puntosInteres.forEach { puntoInteres ->
        val pos = LatLng(puntoInteres.latitud.toDouble(), puntoInteres.longitud.toDouble())
        markers.add(pos to puntoInteres.descripcion)
    }
    return markers
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

fun saveImageToFile(context: Context, imageBytes: ByteArray): String {
    val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    val file = File(context.filesDir, "profile_image.jpg")
    FileOutputStream(file).use { out ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
    }
    return file.absolutePath
}
