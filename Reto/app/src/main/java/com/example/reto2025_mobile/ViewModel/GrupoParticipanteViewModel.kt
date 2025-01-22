package com.example.reto2025_mobile.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.GrupoParticipante
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GrupoParticipanteViewModel: ViewModel() {

    private val service = RetrofitServiceFactory.makeRetrofitService()

    private val _grupoParticipante = MutableLiveData<GrupoParticipante>()
    val grupoParticipante: LiveData<GrupoParticipante> = _grupoParticipante
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

    fun updateGrupoParticipante(grupoParticipante: GrupoParticipante) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                service.updateGrupoParticipante(grupoParticipante.id!!, grupoParticipante)
                _grupoParticipante.postValue(grupoParticipante)
                Log.d("GrupoParticipante", "Updated grupoParticipante: $grupoParticipante")
            } catch (e: Exception) {
                Log.d("GrupoParticipante", "$e")
                e.printStackTrace()
            }
        }
    }


}