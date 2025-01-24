package com.example.reto2025_mobile.Componentes

import com.example.reto2025_mobile.data.Departamento
import com.example.reto2025_mobile.data.Profesor

object Usuario {
    var uuid: String = ""
    var dni: String = ""
    var nombre: String = ""
    var apellidos: String = ""
    var correo: String = ""
    var password: String = ""
    var rol: String = ""
    var activo: Boolean = false
    var urlFoto: String? = ""
    var esJefeDep: Boolean? = false
    var depart: Departamento? = null
}