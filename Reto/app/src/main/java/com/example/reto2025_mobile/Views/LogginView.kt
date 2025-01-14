package com.example.reto2025_mobile.Views

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.reto2025_mobile.R

@Composable
fun LogginView(navController: NavHostController) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3F51B5)) // Cambié el color a un tono más profesional
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp), // Espaciado mayor para dar más aire
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centrar todo el contenido verticalmente
        ) {
            // Logo de la app
            Image(
                painter = painterResource(id = R.drawable.logo), // Reemplaza con el ID de tu logo
                contentDescription = "Logo de la App",
                modifier = Modifier
                    .size(220.dp) // Ajusta el tamaño del logo
                    .padding(bottom = 40.dp)
            )

            // Título con un estilo más profesional
            Text(
                text = "INICIAR SESIÓN",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            // Campo de usuario
            TextField(
                value = user,
                onValueChange = { user = it },
                label = { Text("Usuario") }, // Etiqueta para mayor claridad
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                shape = RoundedCornerShape(8.dp)
            )

            // Campo de contraseña
            TextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Contraseña") }, // Etiqueta para mayor claridad
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                shape = RoundedCornerShape(8.dp)
            )

            // Botón de acceso con estilo profesional
            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(50.dp), // Botón redondeado
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5)) // Color de fondo más serio
            ) {
                Text(
                    text = "Acceder",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            // Enlace de "Olvidé mi contraseña" debajo del botón
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

    }
}