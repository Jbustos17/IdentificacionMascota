package com.example.identificacionmascota.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.identificacionmascota.viewmodel.MascotaViewModel

@Composable
fun ScreenA(navController: NavController, mascotaViewModel: MascotaViewModel) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Registro de Mascota",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    OutlinedTextField(
                        value = mascotaViewModel.nombre,
                        onValueChange = { mascotaViewModel.nombre = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = mascotaViewModel.raza,
                        onValueChange = { mascotaViewModel.raza = it },
                        label = { Text("Raza") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = mascotaViewModel.tamaño,
                        onValueChange = { mascotaViewModel.tamaño = it },
                        label = { Text("Tamaño") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = mascotaViewModel.edad,
                        onValueChange = { mascotaViewModel.edad = it },
                        label = { Text("Edad") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = mascotaViewModel.fotoUrl,
                        onValueChange = { mascotaViewModel.fotoUrl = it },
                        label = { Text("Foto URL") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            navController.navigate("screen_b")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Registrar")
                    }
                }
            }
        }
    }
}
