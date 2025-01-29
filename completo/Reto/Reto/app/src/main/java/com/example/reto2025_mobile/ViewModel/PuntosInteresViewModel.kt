package com.example.reto2025_mobile.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.PuntoInteres
import kotlinx.coroutines.launch

class PuntosInteresViewModel : ViewModel() {

    private val _puntoInteres = MutableLiveData<PuntoInteres>()
    val puntoInteres: LiveData<PuntoInteres> = _puntoInteres
    private val _puntosInteres = MutableLiveData<List<PuntoInteres>>()
    val puntosInteres: LiveData<List<PuntoInteres>> = _puntosInteres

    private val service = RetrofitServiceFactory.makeRetrofitService()

    fun getPuntosInteres() {
        viewModelScope.launch {
            try {
                val puntosInteresList = service.getPuntosInteres()
                _puntosInteres.value = puntosInteresList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun savePuntoInteres(puntoInteres: PuntoInteres) {
        viewModelScope.launch {
            try {
                val response = service.createPuntoInteres(puntoInteres)
                if (response.isSuccessful) {
                    getPuntosInteres()
                    // Manejar respuesta exitosa
                    val savedPuntoInteres = response.body()
                    // Hacer algo con el savedPuntoInteres
                } else {
                    // Manejar respuesta de error
                }
            } catch (e: Exception) {
                // Manejar excepción
            }
        }
    }

    fun deletePuntoInteres(puntoInteres: PuntoInteres) {
        viewModelScope.launch {
            try {
               Log.d("pto", "deletePuntoInteres: ${puntoInteres.id}")
                val response = puntoInteres.id?.let { service.deletePuntoInteres(puntoInteres.id) }
                if (response != null) {
                    if (response.isSuccessful) {
                        getPuntosInteres()
                        // Manejar respuesta exitosa
                        val savedPuntoInteres = response.body()
                        // Hacer algo con el savedPuntoInteres
                    } else {
                        // Manejar respuesta de error
                    }
                }
            } catch (e: Exception) {
                // Manejar excepción
            }
        }
    }

    init {
        getPuntosInteres()
    }

}