package com.example.reto2025_mobile.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.reto2025_mobile.R

sealed class ItemsNav (
        val icono: ImageVector,
        val texto:String,
        val ruta:String
    )
{
    object Item_bottom_nav_acts:ItemsNav(
    Icons.Filled.Menu,"Actividades", "actividades")

    object Item_bottom_nav_home:ItemsNav(
        Icons.Filled.Home,"Home", "home")

    object Item_bottom_nav_misActividades:ItemsNav(
        Icons.Default.Star,"Mis Acts", "misActividades")

    object Item_bottom_nav_perfil:ItemsNav(
        Icons.Filled.Person,"Perfil", "perfil")

    object Item_bottom_nav_calendar:ItemsNav(
        Icons.Filled.DateRange,"Calendario", "calendario")


}
