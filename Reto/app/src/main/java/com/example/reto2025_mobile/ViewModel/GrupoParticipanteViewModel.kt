package com.example.reto2025_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.GrupoParticipante
import kotlinx.coroutines.launch

class GrupoParticipanteViewModel: ViewModel() {

    private val service = RetrofitServiceFactory.makeRetrofitService()

    private val _gruposParticipantes = MutableLiveData<List<GrupoParticipante>>()
    val gruposParticipantes: LiveData<List<GrupoParticipante>> = _gruposParticipantes

    fun getGruposParticipantes() {
        viewModelScope.launch {
            try {
                val gruposParticipantesList = service.getGruposParticipantes()
                _gruposParticipantes.value = gruposParticipantesList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}