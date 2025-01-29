package com.example.reto2025_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.Profesor
import kotlinx.coroutines.launch

class ProfesorViewModel: ViewModel() {

    private val service = RetrofitServiceFactory.makeRetrofitService()

    private val _profesores = MutableLiveData<List<Profesor>>()
    val profesores: LiveData<List<Profesor>> = _profesores

    fun getProfesores() {
        viewModelScope.launch {
            try {
                val profesoresList = service.getProfesores()
                _profesores.value = profesoresList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}