package com.example.reto2025_mobile.ViewModel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.Foto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File
import java.io.InputStream


class FotoViewModel: ViewModel() {

    private val _foto = MutableLiveData<Response<ResponseBody>>()
    val foto: LiveData<Response<ResponseBody>> = _foto
    private val _fotos = MutableLiveData<List<Foto>>()
    val fotos: LiveData<List<Foto>> = _fotos

    private val service = RetrofitServiceFactory.makeRetrofitService()

    val bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())


    fun fetchFotos(idActividad: Int) {
        // Limpiar fotos actuales antes de cargar nuevas
        bitmaps.value = emptyList()

        viewModelScope.launch {
            try {
                val fotos = service.getFotos(idActividad)
                val bitmapsList = fotos.mapNotNull { foto ->
                    try {
                        val response = service.getFoto(idActividad, foto.id)
                        val inputStream: InputStream = response.byteStream()
                        BitmapFactory.decodeStream(inputStream)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        null
                    }
                }
                bitmaps.value = bitmapsList
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

    fun uploadPhoto(
        context: Context,
        idActividad: Int,
        descripcion: String,
        uri: Uri
    ): LiveData<Result<Unit>> {
        val liveData = MutableLiveData<Result<Unit>>()
        viewModelScope.launch {
            try {
                // Preparar archivo como MultipartBody.Part
                val file = File(uri.path!!)
                val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val photoPart = MultipartBody.Part.createFormData("fichero", file.name, requestBody)
                val descripcionPart = descripcion.toRequestBody("text/plain".toMediaTypeOrNull())

                // Llamar a Retrofit
                val response = RetrofitServiceFactory.makeRetrofitService().uploadPhoto(idActividad, descripcionPart, photoPart)
                if (response.isSuccessful) {
                    liveData.postValue(Result.success(Unit))

                } else {
                    liveData.postValue(Result.failure(Exception("Error en la respuesta: ${response.code()}")))
                }
            } catch (e: Exception) {
                liveData.postValue(Result.failure(e))
            }
        }
        return liveData
    }





}

