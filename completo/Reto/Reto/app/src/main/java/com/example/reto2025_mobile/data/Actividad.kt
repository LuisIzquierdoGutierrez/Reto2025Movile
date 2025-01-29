package com.example.reto2025_mobile.data

data class Actividad(
    val id: Int?,
    val titulo: String,
    val tipo: String, // "Complementaria" o "Extraescolar"
    val descripcion: String?,
    val fini: String,
    val ffin: String,
    val hini: String,
    val hfin: String,
    val previstaIni: Boolean,
    val transporteReq: Boolean,
    val comentTransporte: String?,
    val alojamientoReq: Boolean,
    val comentAlojamiento: String?,
    val comentarios: String?,
    val estado: String, // Valores como "SOLICITADA" "APROBADA"...
    val comentEstado: String?,
    var incidencias: String?,
    val urlFolleto: String?,
    val solicitante: Profesor,
    val importePorAlumno: Double?, //Importe que se dara por alumno
    val latitud: String?,
    val longitud: String?,
)
