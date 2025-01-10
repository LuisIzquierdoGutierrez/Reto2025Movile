package com.example.reto2025_mobile.data

data class Profesor(
    val uuid: String,
    val dni: String,
    val nombre: String,
    val apellidos: String,
    val correo: String,
    val password: String,
    val rol: String,
    val activo: Boolean,
    val urlFoto: String?,
    val depId: Int,
    val esJefeDep: Boolean?
)
