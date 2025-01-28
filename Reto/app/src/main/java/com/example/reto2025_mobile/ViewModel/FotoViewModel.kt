package com.example.reto2025_mobile.ViewModel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.data.Foto
import kotlinx.coroutines.launch
import okhttp3.MediaType
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
                Log.d("foto", "uploadPhoto: ${uri.path}")
                Log.d("foto", "uploadPhoto: $idActividad")
                val filePart = prepareFilePart(context, uri)
                // Llama al servicio para subir la foto
                val response: Response<Foto> = service.uploadPhoto(idActividad, descripcion, filePart)
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

    fun prepareFilePart(context: Context, uri: Uri): MultipartBody.Part {
        // Convert Uri to File
        val file = File(getRealPathFromURI(context, uri))

        // Create a RequestBody for the file
        val requestBody = RequestBody.create(
            context.contentResolver.getType(uri)?.let { it.toMediaTypeOrNull() }, file
        )

        // Wrap the file into a MultipartBody.Part
        return MultipartBody.Part.createFormData("fichero", file.name, requestBody)
    }

    fun getRealPathFromURI(context: Context, contentUri: Uri): String {
        val cursor = context.contentResolver.query(contentUri, null, null, null, null)
        cursor?.let {
            it.moveToFirst()
            val index = it.getColumnIndex(android.provider.MediaStore.Images.Media.DATA)
            return it.getString(index)
        }
        return ""
    }

}

