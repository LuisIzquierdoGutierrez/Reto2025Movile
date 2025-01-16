package com.example.reto2025_mobile.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.Profesor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfesorLoginViewModel : ViewModel() {

    private val service = RetrofitServiceFactory.makeRetrofitService()

    private val _loginResult = MutableLiveData<Profesor?>()
    val loginResult: LiveData<Profesor?> = _loginResult

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(correo: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                // Simula un retraso de 2 segundos


                val profesor = withContext(Dispatchers.IO) {
                    service.login(correo, password)
                }
                _loginResult.value = profesor
            } catch (e: Exception) {
                _errorMessage.value = "No se pudo iniciar sesión. Por favor, verifica tus credenciales."
            } finally {
                _isLoading.value = false
            }
        }
    }
}