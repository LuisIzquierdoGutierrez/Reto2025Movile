package com.example.reto2025_mobile.data

data class Grupo(
    val id: Int,
    val curso: Curso,
    val codGrupo: String,
    val numAlumnos: Int,
    val activo: Boolean,
    val tutor: Profesor,
)

