package com.example.reto2025_mobile.Model

data class GrupoParticipante(
    val idGrupo: Int,
    val curso: Int,
    val codGrupo: String,
    val numAlumnos: Int,
    val activo: Boolean,
    val tutor: String
)
