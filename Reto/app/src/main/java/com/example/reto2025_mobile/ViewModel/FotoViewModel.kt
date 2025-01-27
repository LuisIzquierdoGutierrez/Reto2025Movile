package com.example.reto2025_mobile.ViewModel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.Componentes.createPartFromString
import com.example.reto2025_mobile.Componentes.prepareFilePart
import com.example.reto2025_mobile.data.Foto
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File

class FotoViewModel: ViewModel() {
    private val _foto = MutableLiveData<Response<ResponseBody>>()
    val foto: LiveData<Response<ResponseBody>> = _foto
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

    fun uploadPhoto(context: Context, idActividad: Int, descripcion: String, uri: Uri) {
        viewModelScope.launch {
            try {
                // Prepara la parte del archivo
                val filePart = prepareFilePart(context, uri, "fichero")
                // Prepara la parte de la descripción
                val descriptionPart = createPartFromString(descripcion)
                // Llama al servicio para subir la foto
                val response: Response<Foto> = service.uploadPhoto(idActividad, descriptionPart, filePart)
                if (response.isSuccessful) {
                    val foto: Foto? = response.body()
                    // Manejar la instancia de Foto si es necesario
                } else {
                    // Manejar respuesta de error
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun prepareFilePart(context: Context, uri: Uri, partName: String): MultipartBody.Part {
        val file = File(uri.path)
        val requestFile = RequestBody.create(context.contentResolver.getType(uri)?.toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData(partName, file.name, requestFile)
    }

    private fun createPartFromString(description: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), description)
    }
}

