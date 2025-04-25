package com.example.identificacionmascota.Model

import java.util.UUID

data class Mascota(
    val id: String = UUID.randomUUID().toString(),
    val nombre: String,
    val raza: String,
    val tamaño: String,
    val edad: String,
    val fotoUrl: String
)
