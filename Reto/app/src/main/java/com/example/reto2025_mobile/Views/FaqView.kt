package com.example.reto2025_mobile.Views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reto2025_mobile.Componentes.ActividadesTopAppBar
import com.example.reto2025_mobile.Componentes.BottomAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FAQView(navController: NavController) {
    Scaffold (
        topBar = { ActividadesTopAppBar(navController) },
        bottomBar = { BottomAppBar(navController = navController) }
    ){
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            // Título de la normativa
            Text(
                text = "Normativa",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 80.dp)


            )

            // Caja de la normativa
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = MaterialTheme.shapes.medium.copy(CornerSize(16.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = """
                        La normativa relativa a las ACEX está recogida en el documento que contiene las Normas de Organización y Funcionamiento (NOF) de nuestro centro...
                        
                        Además, el alumnado de cada actividad extraescolar debe conocer las normas de seguridad e higiene, el protocolo de emergencias en caso de accidentes, las fechas y horarios de las actividades, las condiciones para el uso del transporte y los requisitos específicos de cada actividad.
                    """.trimIndent(),
                        style = MaterialTheme.typography.bodyLarge,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Botón para descargar PDF
                    Button(
                        onClick = {  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(text = "Descargar PDF")
                    }
                }
            }

            // Titulo de FAQ
            Text(
                text = "F.A.Q",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Sección de preguntas frecuentes usando LazyColumn para desplazamiento
            LazyColumn(
                modifier = Modifier.fillMaxHeight().padding(bottom = 65.dp)
            ) {
                item {
                    FAQItem(
                        question = "¿CÓMO INICIO LA PREPARACIÓN DE UNA ACTIVIDAD EXTRAESCOLAR?",
                        answer = """
                        OPCIÓN 1: PROFESORADO USUARIO DE TEAMS (PC/MÓVIL). El profesorado responsable debe rellenar y enviar el formulario correspondiente con los datos necesarios para la actividad. 
                        OPCIÓN 2: DIRECTORES DE ACEX. En caso de ser necesaria la intervención de los directores de ACEX, deben recibir la solicitud con la antelación necesaria para su evaluación.
                    """.trimIndent()
                    )
                }
                item {
                    FAQItem(
                        question = "¿CUÁNDO INICIO LA PREPARACIÓN DE UNA ACTIVIDAD EXTRAESCOLAR?",
                        answer = """
                        El/la profesor/a responsable enviará el formulario al menos quince días antes del inicio de la actividad extraescolar, para permitir la organización adecuada.
                    """.trimIndent()
                    )
                }
                item {
                    FAQItem(
                        question = "¿LOS ALUMNOS PRECISAN AUTORIZACIÓN PARA ASISTIR A LA ACTIVIDAD EXTRAESCOLAR?",
                        answer = """
                        Los padres y tutores legales pueden autorizar en el impreso de la matrícula la participación de sus hijos en las actividades extraescolares. 
                        Es necesario tener la autorización firmada para poder asistir a la actividad.
                    """.trimIndent()
                    )
                }
                item {
                    FAQItem(
                        question = "¿QUÉ DEBO HACER SI QUIERO ANULAR UNA ACTIVIDAD?",
                        answer = """
                        Si la actividad debe ser anulada, el profesorado responsable debe notificar la cancelación a los responsables de ACEX y a los participantes al menos 24 horas antes del evento.
                    """.trimIndent()
                    )
                }
            }
        }
    }

}

@Composable
fun FAQItem(question: String, answer: String) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        // Pregunta en negrita
        Text(
            text = question,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 4.dp)
        )
        // Respuesta normal
        Text(
            text = answer,
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 22.sp
        )
    }
}