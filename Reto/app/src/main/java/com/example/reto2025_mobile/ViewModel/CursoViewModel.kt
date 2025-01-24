package com.example.reto2025_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.Curso
import kotlinx.coroutines.launch

class CursoViewModel: ViewModel() {

    private val service = RetrofitServiceFactory.makeRetrofitService()

    private val _cursos = MutableLiveData<List<Curso>>()
    val cursos: LiveData<List<Curso>> = _cursos

    fun getCursos() {
        viewModelScope.launch {
            try {
                val cursosList = service.getCursos()
                _cursos.value = cursosList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}