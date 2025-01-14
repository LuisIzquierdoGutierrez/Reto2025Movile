package com.example.reto2025_mobile.Componentes

import android.app.Activity
import android.telecom.Call.Details
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.reto2025_mobile.Navigation.ItemsNav
import com.example.reto2025_mobile.R
import io.github.boguszpawlowski.composecalendar.Calendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import java.time.LocalDate

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                "nombre Actividad",
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
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "añadir imagenes"
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.photo),
                            contentDescription = "añadir imagenes"
                        )
                    }
                }
            }
        },
    )
}

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

@Composable
fun currentRoute(navController: NavController) :String? =
    navController.currentBackStackEntryAsState().value?.destination?.route

@Composable
fun BottomAppBar(navController: NavController) {
    val bar_items = listOf(
        ItemsNav.Item_bottom_nav_home,
        //ItemsNav.Item_bottom_nav_prox,
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

@Composable
fun ActivityCalendarApp() {
    // Estado para las actividades (con título y horario)
    var activities by remember { mutableStateOf(mapOf<LocalDate, Activity>()) }

    // Estado del calendario
    val calendarState = rememberCalendarState()

    // Estado del día seleccionado
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Mostrar el calendario
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

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar información sobre el día seleccionado
        selectedDate?.let { date ->
            ActivityDetails(
                date = date,
                activity = activities[date],
                onAddActivity = { title, time ->
                    // Añadir actividad con título y horario
                    //activities = activities + (date to Activity(title, time))
                },
                onRemoveActivity = {
                    // Eliminar actividad
                    activities = activities - date
                }
            )
        }

    }
}

@Composable
fun MyDayContentWithActivities(
    dayState: DayState<*>,
    activities: Map<LocalDate, Activity>,
    onClick: () -> Unit
) {
    val hasActivity = activities.containsKey(dayState.date)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = onClick) // Hacer clic en el día
    ) {
        Text(
            text = dayState.date.dayOfMonth.toString(),
            style = MaterialTheme.typography.bodyMedium
        )

        // Si tiene actividad, mostrar un punto debajo del número
        if (hasActivity) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun ActivityDetails(
    date: LocalDate,
    activity: Activity?,
    onAddActivity: (String, String) -> Unit,
    onRemoveActivity: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        
    ) {
        Text(text = "Actividad para ${date.dayOfMonth}-${date.monthValue}-${date.year}")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title)

        // Si ya hay una actividad, mostrar el título y el horario
        activity?.let {
            Text(text = "Título: ${it.title}")
            //Text(text = "Horario: ${it.time}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onRemoveActivity,
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text(text = "Eliminar actividad")
            }
        } ?: run {
            // Si no hay actividad, mostrar los campos para añadirla
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título de la actividad") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = time,
                onValueChange = { time = it },
                label = { Text("Horario de la actividad") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onAddActivity(title, time) },
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text(text = "Añadir actividad")
            }
        }
    }
}