package com.example.identificacionmascota.Screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.identificacionmascota.viewmodel.MascotaViewModel

@Composable
fun ScreenA(navController: NavController, mascotaViewModel: MascotaViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {

        mascotaViewModel.limpiarDatos()
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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
                        value = mascotaViewModel.nombre.value,
                        onValueChange = { mascotaViewModel.nombre.value = it },
                        label = { Text("Nombre") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )

                    OutlinedTextField(
                        value = mascotaViewModel.raza.value,
                        onValueChange = { mascotaViewModel.raza.value = it },
                        label = { Text("Raza") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )

                    OutlinedTextField(
                        value = mascotaViewModel.tamaño.value,
                        onValueChange = { mascotaViewModel.tamaño.value = it },
                        label = { Text("Tamaño") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )

                    OutlinedTextField(
                        value = mascotaViewModel.edad.value,
                        onValueChange = { mascotaViewModel.edad.value = it },
                        label = { Text("Edad") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )

                    OutlinedTextField(
                        value = mascotaViewModel.fotoUrl.value,
                        onValueChange = { mascotaViewModel.fotoUrl.value = it },
                        label = { Text("Foto URL") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {
                            keyboardController?.hide()
                        })
                    )


                    if (errorMessage.isNotEmpty()) {
                        Text(
                            text = errorMessage,
                            color = Color.Red,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (mascotaViewModel.nombre.value.isNotBlank() &&
                                mascotaViewModel.raza.value.isNotBlank() &&
                                mascotaViewModel.tamaño.value.isNotBlank() &&
                                mascotaViewModel.edad.value.isNotBlank() &&
                                mascotaViewModel.fotoUrl.value.isNotBlank()
                            ) {
                                errorMessage = ""
                                mascotaViewModel.agregarMascota()
                                navController.navigate("screen_b")
                            } else {
                                errorMessage = "Por favor, completa todos los campos."
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF0D47A1),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Registrar")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Registrar")
                    }
                }
            }
        }
    }
}
