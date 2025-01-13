package com.example.reto2025_mobile.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ItemsNav (
        val icono: ImageVector,
        val texto:String,
        val ruta:String
    )
    {
        object Item_bottom_nav_home:ItemsNav(
            Icons.Filled.Home,"Home", "home")
        object Item_bottom_nav_prox:ItemsNav(
            Icons.Filled.DateRange,"Prox", "proximas")
        object Item_bottom_nav_acts:ItemsNav(
            Icons.Filled.List,"Acts", "actividades")


    }
