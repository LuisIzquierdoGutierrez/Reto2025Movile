package com.example.reto2025_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.Departamento
import kotlinx.coroutines.launch

class DepartamentoViewModel: ViewModel() {

    private val service = RetrofitServiceFactory.makeRetrofitService()

    private val _departamentos = MutableLiveData<List<Departamento>>()
    val departamentos: LiveData<List<Departamento>> = _departamentos

    fun getDepartamentos() {
        viewModelScope.launch {
            try {
                val departamentosList = service.getDepartamentos()
                _departamentos.value = departamentosList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}