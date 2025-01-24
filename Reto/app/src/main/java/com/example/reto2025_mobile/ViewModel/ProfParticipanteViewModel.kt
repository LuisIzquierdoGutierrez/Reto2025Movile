package com.example.reto2025_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.ProfParticipante
import kotlinx.coroutines.launch

class ProfParticipanteViewModel: ViewModel() {

    private val service = RetrofitServiceFactory.makeRetrofitService()

    private val _profesoresParticipantes = MutableLiveData<List<ProfParticipante>>()
    val profesoresParticipantes: LiveData<List<ProfParticipante>> = _profesoresParticipantes

    fun getProfesoresParticipantes() {
        viewModelScope.launch {
            try {
                val profesoresParticipantesList = service.getProfesoresParticipantes()
                _profesoresParticipantes.value = profesoresParticipantesList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}