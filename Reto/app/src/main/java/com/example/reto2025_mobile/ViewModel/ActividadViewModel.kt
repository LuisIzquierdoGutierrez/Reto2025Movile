package com.example.reto2025_mobile.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.Actividad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActividadViewModel:ViewModel() {


    private val _actividad = MutableLiveData<Actividad>()
    val actividad: LiveData<Actividad> = _actividad
    private val _actividades = MutableLiveData<List<Actividad>>()
    val actividades: LiveData<List<Actividad>> = _actividades

    private val service = RetrofitServiceFactory.makeRetrofitService()

    fun getActividades() {
        viewModelScope.launch {
            try {
                val actividadesList = service.getActividades()
                _actividades.value = actividadesList
                Log.d("Actividades", "Received list: $actividadesList")
            } catch (e: Exception) {
                Log.d("Actividades", "$e")
                e.printStackTrace()
            }
        }
    }

    fun getActividadById(id: Int?) {
        viewModelScope.launch {
            try {
                _actividad.value = service.getActividadById(id!!)// las dos exclamaciones son para que no sea nulo
                Log.d("Actividades", "Received list: $actividad")
            } catch (e: Exception) {
                Log.d("Actividades", "$e")
                e.printStackTrace()
            }
        }
    }

    fun updateActividad(actividad: Actividad) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                service.updateActividad(actividad.id!!, actividad)
                _actividad.postValue(actividad)

                Log.d("Actividades", "Updated actividad: $actividad")
            } catch (e: Exception) {
                Log.d("Actividades", "$e")
                e.printStackTrace()
            }
        }
    }

    fun getActividadById(id: Int?) {
        viewModelScope.launch {
            try {
                _actividad.value = service.getActividadById(id!!)// las dos exclamaciones son para que no sea nulo
                Log.d("Actividades", "Received list: $actividad")
            } catch (e: Exception) {
                Log.d("Actividades", "$e")
                e.printStackTrace()
            }
        }
    }

    fun updateActividad(actividad: Actividad) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                service.updateActividad(actividad.id!!, actividad)
                // las siguientes lineas

                Log.d("Actividades", "Updated actividad: $actividad")
            } catch (e: Exception) {
                Log.d("Actividades", "$e")
                e.printStackTrace()
            }
        }
    }

    init {
        getActividades()
    }
}