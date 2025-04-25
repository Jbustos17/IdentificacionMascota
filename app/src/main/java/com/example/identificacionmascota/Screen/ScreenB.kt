package com.example.identificacionmascota.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.identificacionmascota.Model.Mascota
import com.example.identificacionmascota.viewmodel.MascotaViewModel

@Composable
fun ScreenB(navController: NavController, mascotaViewModel: MascotaViewModel) {

    val listaMascotas = mascotaViewModel.listaMascotas.value
    var mascotaAEditar by remember { mutableStateOf<Mascota?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var mascotaAEliminar by remember { mutableStateOf<Mascota?>(null) }

    Scaffold(
        bottomBar = {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1))
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Volver", color = Color.White)
            }
        }
    ) { paddingValues ->
        if (listaMascotas.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(listaMascotas.size) { index ->
                    val mascota = listaMascotas[index]

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = mascota.fotoUrl,
                                contentDescription = "Foto de mascota",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = mascota.nombre,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF0D47A1)
                            )
                            Text("Raza: ${mascota.raza}")
                            Text("Tamaño: ${mascota.tamaño}")
                            Text("Edad: ${mascota.edad} años")

                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Button(
                                    onClick = {
                                        mascotaAEliminar = mascota
                                        showDialog = true
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                                    modifier = Modifier.padding(end = 12.dp)
                                ) {
                                    Text("Eliminar", color = Color.White)
                                }

                                Button(
                                    onClick = { mascotaAEditar = mascota },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))
                                ) {
                                    Text("Editar", color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("No hay mascotas registradas.")
            }
        }


        if (showDialog && mascotaAEliminar != null) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            mascotaAEliminar?.let {
                                mascotaViewModel.eliminarMascota(it)
                            }
                            showDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))
                    ) {
                        Text("Confirmar", color = Color.White)
                    }
                },
                dismissButton = {
                    OutlinedButton(
                        onClick = { showDialog = false },
                    ) {
                        Text("Cancelar")
                    }
                },
                title = { Text("¿Estás seguro de eliminar esta mascota?") },
                text = { Text("Esta acción no se puede deshacer.") }
            )
        }


        mascotaAEditar?.let { mascota ->
            EditarMascotaDialog(
                mascota = mascota,
                onDismiss = { mascotaAEditar = null },
                onGuardar = { mascotaEditada ->
                    mascotaViewModel.editarMascota(mascotaEditada)
                    mascotaAEditar = null
                }
            )
        }
    }
}
@Composable
fun EditarMascotaDialog(
    mascota: Mascota,
    onDismiss: () -> Unit,
    onGuardar: (Mascota) -> Unit
) {
    var nombre by remember { mutableStateOf(TextFieldValue(mascota.nombre)) }
    var raza by remember { mutableStateOf(TextFieldValue(mascota.raza)) }
    var tamaño by remember { mutableStateOf(TextFieldValue(mascota.tamaño)) }
    var edad by remember { mutableStateOf(TextFieldValue(mascota.edad)) }
    var fotoUrl by remember { mutableStateOf(TextFieldValue(mascota.fotoUrl)) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    onGuardar(
                        mascota.copy(
                            nombre = nombre.text,
                            raza = raza.text,
                            tamaño = tamaño.text,
                            edad = edad.text,
                            fotoUrl = fotoUrl.text
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0)) // Azul
            ) {
                Text("Guardar", color = Color.White)
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = { Text("Editar Mascota") },
        text = {
            Column {
                OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
                OutlinedTextField(value = raza, onValueChange = { raza = it }, label = { Text("Raza") })
                OutlinedTextField(value = tamaño, onValueChange = { tamaño = it }, label = { Text("Tamaño") })
                OutlinedTextField(value = edad, onValueChange = { edad = it }, label = { Text("Edad") })
                OutlinedTextField(value = fotoUrl, onValueChange = { fotoUrl = it }, label = { Text("Foto URL") })
            }
        },
        shape = RoundedCornerShape(16.dp)
    )
}
