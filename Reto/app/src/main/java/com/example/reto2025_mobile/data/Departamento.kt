package com.example.reto2025_mobile.data

data class Departamento(
    val idDepar: Int,
    val codigo: String,
    val nombre: String,
    val jefe_dep: Profesor
)

