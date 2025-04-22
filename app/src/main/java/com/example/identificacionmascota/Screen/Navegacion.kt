package com.example.identificacionmascota.Screen


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.identificacionmascota.Screen.ScreenA
import com.example.identificacionmascota.Screen.ScreenB
import com.example.identificacionmascota.viewmodel.MascotaViewModel

@Composable
fun Navegacion() {
    val navController = rememberNavController()
    val mascotaViewModel: MascotaViewModel = viewModel()

    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") {
            ScreenA(navController, mascotaViewModel)
        }
        composable("screen_b") {
            ScreenB(navController, mascotaViewModel)
        }
    }
}
