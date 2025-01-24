package com.example.reto2025_mobile.data

data class Contrato(
    val id: Int,
    val actividad: Actividad,
    val empTransporteId: EmpTransporte,
    val contratada: Boolean,
    val importe: Double,
    val urlPresupuesto: String?,
    val urlFactura: String?
)

