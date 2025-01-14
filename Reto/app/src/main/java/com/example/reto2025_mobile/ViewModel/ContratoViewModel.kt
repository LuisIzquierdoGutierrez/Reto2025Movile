package com.example.reto2025_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.Actividad
import com.example.reto2025_mobile.data.Contrato
import kotlinx.coroutines.launch

class ContratoViewModel: ViewModel() {

    private val service = RetrofitServiceFactory.makeRetrofitService()

    private val _contratos = MutableLiveData<List<Contrato>>()
    val contratos: LiveData<List<Contrato>> = _contratos

    fun getContratos() {
        viewModelScope.launch {
            try {
                val contratosList = service.getContratos()
                _contratos.value = contratosList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}