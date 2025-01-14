package com.example.reto2025_mobile.API

import com.example.reto2025_mobile.data.Actividad
import com.example.reto2025_mobile.data.Contrato
import com.example.reto2025_mobile.data.Curso
import com.example.reto2025_mobile.data.Departamento
import com.example.reto2025_mobile.data.GrupoParticipante
import com.example.reto2025_mobile.data.ProfParticipante
import com.example.reto2025_mobile.data.ProfResponsable
import com.example.reto2025_mobile.data.Profesor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("actividades")
    suspend fun getActividades(): List<Actividad>

    @GET("contratos")
    suspend fun getContratos(): List<Contrato>

    @GET("cursos")
    suspend fun getCursos(): List<Curso>

    @GET("departamentos")
    suspend fun getDepartamentos(): List<Departamento>

    @GET("gruposParticipantes")
    suspend fun getGruposParticipantes(): List<GrupoParticipante>

    @GET("profesores")
    suspend fun getProfesores(): List<Profesor>

    @GET("profesoresParticipantes")
    suspend fun getProfesoresParticipantes(): List<ProfParticipante>

    @GET("profesoresResponsables")
    suspend fun getProfesoresResponsables(): List<ProfResponsable>
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("http://10.0.22.67:8080/acex/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}