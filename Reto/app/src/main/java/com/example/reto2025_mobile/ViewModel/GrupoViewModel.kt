package com.example.reto2025_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.Grupo
import kotlinx.coroutines.launch

class GrupoViewModel: ViewModel() {
    private val _grupos = MutableLiveData<List<Grupo>>()
    val grupos: LiveData<List<Grupo>> = _grupos

    private val service = RetrofitServiceFactory.makeRetrofitService()

    fun getGrupos() {
        viewModelScope.launch {
            try {
                val gruposList = service.getGrupos()
                _grupos.value = gruposList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}