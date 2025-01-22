package com.example.reto2025_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.Foto
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class FotoViewModel: ViewModel() {
    private val _fotos = MutableLiveData<List<Foto>>()
    val fotos: LiveData<List<Foto>> = _fotos

    private val service = RetrofitServiceFactory.makeRetrofitService()

    fun getFotoActividad(actividadId: Int, id : Int) {
        viewModelScope.launch {
            try {
                val foto = service.getFotoActividad(actividadId,id)
                _foto.value = foto
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getFotos() {
        viewModelScope.launch {
            try {
                val fotosList = service.getFotos()
                _fotos.value = fotosList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    init {
        getFotos()
    }
}