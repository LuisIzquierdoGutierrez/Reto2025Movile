package com.example.reto2025_mobile.Componentes

import android.telecom.Call.Details
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
fun HomeAppBar() {
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
        )
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
        ItemsNav.Item_bottom_nav_acts
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