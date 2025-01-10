package com.example.reto2025_mobile.data

data class Actividad(
    val id: Int?,
    val titulo: String,
    val tipo: String, // "Complementaria" o "Extraescolar"
    val descripcion: String,
    val fechaInicio: String,
    val fechaFin: String,
    val previstaIni: Boolean,
    val transporteReq: Boolean,
    val comentTransporte: String?,
    val alojamientoReq: Boolean,
    val comentAlojamiento: String?,
    val comentarios: String?,
    val estado: String, // Valores como "SOLICITADA" "APROBADA"...
    val comentEstado: String?,
    val incidencias: String?,
    val urlFolleto: String?,
    val solicitanteId: String, // ID de quien solicite la actividad
    val importePorAlumno: Double //Importe que se dara por alumno
)
