package com.example.reto2025_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.Actividad
import kotlinx.coroutines.launch

class ActividadViewModel:ViewModel() {

   private val service = RetrofitServiceFactory.makeRetrofitService()

    private val _actividades = MutableLiveData<List<Actividad>>()
    val actividades: LiveData<List<Actividad>> = _actividades

    fun getActividades() {
        viewModelScope.launch {
            try {
                val actividadesList = service.getActividades()
                _actividades.value = actividadesList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}