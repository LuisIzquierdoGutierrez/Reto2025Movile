package com.example.reto2025_mobile.Views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reto2025_mobile.Componentes.ActivityCalendarApp
import com.example.reto2025_mobile.Componentes.BottomAppBar
import com.example.reto2025_mobile.Componentes.HomeAppBar
import com.example.reto2025_mobile.ViewModel.ActividadViewModel
import com.example.reto2025_mobile.ViewModel.GrupoParticipanteViewModel
import com.example.reto2025_mobile.ViewModel.ProfParticipanteViewModel
import com.example.reto2025_mobile.data.Actividad
import com.example.reto2025_mobile.ui.theme.GreenContainer
import java.time.LocalDate

@Composable
fun HomeView(
    navController: NavController,
    actividadViewModel: ActividadViewModel,
    profParticipanteViewModel: ProfParticipanteViewModel,
    grupoParticipanteViewModel: GrupoParticipanteViewModel
) {
    val actividades: List<Actividad> by actividadViewModel.actividades.observeAsState(emptyList());
    val hayActividades = actividades.isNotEmpty()

    Scaffold (
        topBar = { HomeAppBar(navController) },
        bottomBar = { BottomAppBar(navController = navController) }
    ){
        innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                ) {
<<<<<<< Updated upstream
                Text(text = "Para Hoy ${LocalDate.now().toString()}",
=======

                    LazyRow {
                        items(actividades) { actividad ->

                            // Cargar las fotos cuando el ID de la actividad cambie
                            LaunchedEffect(actividad.id) {
                                fotoViewModel.getFotos()
                            }
                            // Observar los datos de las fotos desde el ViewModel
                            val fotos by fotoViewModel.fotos.observeAsState(emptyList())


                            if (fotos.isNotEmpty()) {

                                LaunchedEffect(actividad.id) {
                                    fotoViewModel.getFotoActividad(11,2)
                                }

                                Card(
                                    modifier = Modifier
                                        .padding(3.dp)
                                        .size(70.dp),
                                    shape = CircleShape,
                                    colors = CardDefaults.cardColors(containerColor = BlueContainer),
                                    onClick = {
                                        selectedFotos = fotos
                                        selectedIndex = 0
                                        showPic = true
                                    }
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                       /* Image(
                                            painter = rememberAsyncImagePainter(
                                                model = fotos[0].urlFoto,
                                                placeholder = painterResource(id = 1),
                                                error = painterResource(id = R.drawable.logo)
                                            ),
                                            contentDescription = "Imagen de actividad",
                                            modifier = Modifier
                                                .size(70.dp)
                                                .clip(CircleShape)
                                        )*/
                                    }
                                }
                            } else {
                                // Mostrar un círculo vacío si no hay fotos
                                Card(
                                    modifier = Modifier
                                        .padding(3.dp)
                                        .size(70.dp),
                                    shape = CircleShape,
                                    colors = CardDefaults.cardColors(containerColor = BlueContainer)
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.AccountCircle,
                                            contentDescription = "No hay fotos",
                                            modifier = Modifier.size(32.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }


                if (showPic) {
                    Dialog(onDismissRequest = { showPic = false }) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black)
                                .clickable { showPic = false },
                            contentAlignment = Alignment.Center
                        ) {
                            selectedFotos?.let { fotos ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(model = fotos[selectedIndex].urlFoto),
                                        contentDescription = "Full screen photo",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f)
                                            .padding(16.dp)
                                    )
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        if (selectedIndex > 0) {
                                            Button(onClick = {
                                                selectedIndex -= 1
                                            }) {
                                                Text(text = "Anterior")
                                            }
                                        }
                                        if (selectedIndex < fotos.size - 1) {
                                            Button(onClick = {
                                                selectedIndex += 1
                                            }) {
                                                Text(text = "Siguiente")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


                Text(
                    text = "Bienvenido/a  ${Usuario.nombre}",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2))
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil",
                            modifier = Modifier.size(100.dp),
                            tint = Color.Gray
                        )
                    }
                }


                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "Actividades Próximas",
>>>>>>> Stashed changes
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally) )
                if(hayActividades){
                    LazyColumn (modifier = Modifier.weight(0.3f)){
                        items(actividades) { actividad ->
                            if(actividad.fini == LocalDate.now().toString()){
                                Card (modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                                    .fillMaxHeight(),
                                    shape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp),
                                    colors = CardDefaults.cardColors(containerColor = GreenContainer),
                                    onClick = {
                                        actividadViewModel.getActividadById(actividad.id)
                                        profParticipanteViewModel.getProfesoresParticipantes()
                                        grupoParticipanteViewModel.getGruposParticipantes()
                                        navController.navigate("details") })
                                {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        Column {
                                            Text(
                                                text = actividad.titulo,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFF000000),
                                                textAlign = TextAlign.Start
                                            )
                                        }
                                    }
                                }
                            }else{

                            }

                        }
                    }
                }else{
                    Card (modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .fillMaxHeight(),
                        shape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp),
                        colors = CardDefaults.cardColors(containerColor = GreenContainer),
                        onClick = {})
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Column {
                                Text(
                                    text = "No hay actividades programadas para hoy",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF000000),
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
                LazyColumn (modifier = Modifier.weight(0.7f)){
                    items(actividades) { actividad ->
                        if(actividad.fini == LocalDate.now().toString()){
                            Card (modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                                .fillMaxHeight(),
                                shape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp),
                                colors = CardDefaults.cardColors(containerColor = GreenContainer),
                                onClick = {
                                    actividadViewModel.getActividadById(actividad.id)
                                    profParticipanteViewModel.getProfesoresParticipantes()
                                    grupoParticipanteViewModel.getGruposParticipantes()
                                    navController.navigate("details") })
                            {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Column {
                                        Text(
                                            text = actividad.titulo,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                }
                            }
                        }else{

                        }

                    }
                }

                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Actividades Proximas",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally) )
                LazyColumn (modifier = Modifier.weight(0.7f)){
                    items(actividades) { actividad ->
                        if(actividad.fini >= LocalDate.now().plusDays(1).toString() && actividad.fini <= LocalDate.now().plusDays(8).toString()){
                            Card (modifier = Modifier
                                .weight(1f)
                                .padding(8.dp)
                                .fillMaxHeight(),
                                shape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp),
                                colors = CardDefaults.cardColors(containerColor = GreenContainer),
                                onClick = {
                                    actividadViewModel.getActividadById(actividad.id)
                                    profParticipanteViewModel.getProfesoresParticipantes()
                                    grupoParticipanteViewModel.getGruposParticipantes()
                                    navController.navigate("details") })
                            {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Column {
                                        Text(
                                            text = actividad.titulo,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Start
                                        )
                                        Text(
                                            text = "Fecha Actividad: ${actividad.fini}",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        BackHandler {

        }
    }
}


