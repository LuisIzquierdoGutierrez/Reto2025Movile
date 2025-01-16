package com.example.reto2025_mobile.data

data class GrupoParticipante(
    val id: Int?,
    val actividades: Actividad,
    val grupo: Grupo,
    val numParticipantes: Int,
    val comentarios: String?
)
