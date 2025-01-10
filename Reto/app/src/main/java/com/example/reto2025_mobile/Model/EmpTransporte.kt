package com.example.reto2025_mobile.Model

data class EmpTransporte(
    val id: Int,
    val nombre: String,
    val cif: String,
    val direccion: String?,
    val cp: String?,
    val localidad: String?,
    val contacto: String?
)

