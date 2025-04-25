package com.example.identificacionmascota.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.UUID
import com.example.identificacionmascota.Model.Mascota


class MascotaViewModel : ViewModel() {
    var nombre = mutableStateOf("")
    var raza = mutableStateOf("")
    var tamaño = mutableStateOf("")
    var edad = mutableStateOf("")
    var fotoUrl = mutableStateOf("")

    var listaMascotas = mutableStateOf(listOf<Mascota>())

    fun limpiarDatos() {
        nombre.value = ""
        raza.value = ""
        tamaño.value = ""
        edad.value = ""
        fotoUrl.value = ""
    }

    fun agregarMascota() {
        val nuevaMascota = Mascota(
            nombre = nombre.value,
            raza = raza.value,
            tamaño = tamaño.value,
            edad = edad.value,
            fotoUrl = fotoUrl.value
        )
        listaMascotas.value = listaMascotas.value + nuevaMascota
    }

    fun eliminarMascota(mascota: Mascota) {
        listaMascotas.value = listaMascotas.value.filterNot { it.id == mascota.id }
    }

    fun editarMascota(mascotaEditada: Mascota) {
        val lista = listaMascotas.value.toMutableList()
        val index = lista.indexOfFirst { it.id == mascotaEditada.id }
        if (index != -1) {
            lista[index] = mascotaEditada
            listaMascotas.value = lista
        }
    }
}


