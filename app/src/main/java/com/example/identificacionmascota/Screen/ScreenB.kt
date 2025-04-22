package com.example.identificacionmascota.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.identificacionmascota.viewmodel.MascotaViewModel

@Composable
fun ScreenB(navController: NavController, mascotaViewModel: MascotaViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                AsyncImage(
                    model = mascotaViewModel.fotoUrl,
                    contentDescription = "Foto de la Mascota",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Nombre: ${mascotaViewModel.nombre}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Raza: ${mascotaViewModel.raza}", fontSize = 18.sp)
                Text(text = "Tamaño: ${mascotaViewModel.tamaño}", fontSize = 18.sp)
                Text(text = "Edad: ${mascotaViewModel.edad}", fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Volver")
        }
    }
}
