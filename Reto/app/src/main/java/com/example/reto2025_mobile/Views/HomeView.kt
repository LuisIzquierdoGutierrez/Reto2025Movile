package com.example.reto2025_mobile.Views

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reto2025_mobile.Componentes.ActivityCalendarApp
import com.example.reto2025_mobile.Componentes.AppBar
import com.example.reto2025_mobile.Componentes.BottomAppBar
import com.example.reto2025_mobile.Componentes.HomeAppBar
import com.example.reto2025_mobile.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun HomeView(navController: NavController) {
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
                .height(1000.dp)
                ) {
                Spacer(modifier = Modifier.size(20.dp))
                Box(modifier = Modifier.weight(0.5f)){
                    ActivityCalendarApp()
                    // imagen para comprobar espacio
                    /*Image(
                        painter = painterResource(id = R.drawable.imagen),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentScale = ContentScale.Crop
                    )*/
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Actividades Proximas",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally) )
                LazyColumn (modifier = Modifier.weight(0.5f)){
                    items(10) {
                        Card (modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .fillMaxHeight(),
                            shape = RoundedCornerShape(12.dp, 12.dp, 12.dp, 12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8F2)),
                            onClick = { navController.navigate("details") })
                        {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column {
                                    Text(
                                        text = "Actividad ${it + 1}",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF000000),
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = "Fecha Actividad ${it + 1}",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF000000),
                                        textAlign = TextAlign.Center
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


