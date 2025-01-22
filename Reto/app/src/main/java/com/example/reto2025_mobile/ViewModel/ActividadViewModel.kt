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
                _actividades.value = actividadesList
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