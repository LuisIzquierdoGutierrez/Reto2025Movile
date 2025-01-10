package com.example.reto2025_mobile.data

data class Contrato(
    val id: Int,
    val actividadId: Int,
    val empTransporteId: Int,
    val contratada: Boolean,
    val importe: Double,
    val urlPresupuesto: String?,
    val urlFactura: String?
)

