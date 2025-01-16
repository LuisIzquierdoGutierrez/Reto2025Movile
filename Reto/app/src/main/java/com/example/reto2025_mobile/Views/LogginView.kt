package com.example.reto2025_mobile.Views

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.reto2025_mobile.API.RetrofitServiceFactory
import com.example.reto2025_mobile.R
import com.example.reto2025_mobile.ViewModel.ProfesorLoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.format.TextStyle

@Composable
fun LogginView(
    navController: NavHostController,
    profesorLoginViewModel: ProfesorLoginViewModel = viewModel()
) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val context = LocalContext.current
    val loginResult by profesorLoginViewModel.loginResult.observeAsState()
    val errorMessage by profesorLoginViewModel.errorMessage.observeAsState()
    val isLoading by profesorLoginViewModel.isLoading.observeAsState(false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3F51B5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagen),
                contentDescription = "Logo de la App",
                modifier = Modifier
                    .size(220.dp)
                    .padding(bottom = 40.dp)
            )

            Text(
                text = "INICIAR SESIÓN",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            TextField(
                value = user,
                onValueChange = { user = it },
                label = { Text("Usuario") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(8.dp)
            )

            TextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                shape = RoundedCornerShape(8.dp)
            )

            Button(
                onClick = { profesorLoginViewModel.login(user, pass) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
            ) {
                Text(
                    text = "Acceder",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
            } else {
                errorMessage?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        textAlign = TextAlign.Justify,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                loginResult?.let {
                    // Aquí puedes manejar la navegación o cualquier acción después de un inicio de sesión exitoso
                    navController.navigate("home")
                }
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = "¿Olvidaste tu contraseña?",
                fontSize = 16.sp,
                color = Color(0xFFBBDEFB),
                modifier = Modifier.clickable {
                    // Acción de redirección aquí
                }
            )
        }
    }
    BackHandler {
        // Manejo del botón de retroceso
    }
}