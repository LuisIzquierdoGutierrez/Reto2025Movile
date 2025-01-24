package com.example.reto2025_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.EmpTransporte
import kotlinx.coroutines.launch

class EmpTransporteViewModel: ViewModel() {
    private val _empTransporte = MutableLiveData<List<EmpTransporte>>()
    val empTransporte: LiveData<List<EmpTransporte>> = _empTransporte

    private val service = RetrofitServiceFactory.makeRetrofitService()

    fun getEmpTransporte() {
        viewModelScope.launch {
            try {
                val empTransporteList = service.getTransportes()
                _empTransporte.value = empTransporteList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}