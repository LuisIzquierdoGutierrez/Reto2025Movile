package com.example.reto2025_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.ProfResponsable
import kotlinx.coroutines.launch

class ProfResponsableViewModel: ViewModel() {

    private val service = RetrofitServiceFactory.makeRetrofitService()

    private val _profesoresResponsables = MutableLiveData<List<ProfResponsable>>()
    val profesoresResponsables: LiveData<List<ProfResponsable>> = _profesoresResponsables

    fun getProfesoresResponsables() {
        viewModelScope.launch {
            try {
                val profesoresResponsablesList = service.getProfesoresResponsables()
                _profesoresResponsables.value = profesoresResponsablesList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}