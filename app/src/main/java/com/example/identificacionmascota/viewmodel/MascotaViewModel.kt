package com.example.identificacionmascota.viewmodel

import androidx.lifecycle.ViewModel

class MascotaViewModel : ViewModel() {
    var nombre: String = ""
    var raza: String = ""
    var tamaño: String = ""
    var edad: String = ""
    var fotoUrl: String = ""

    fun registrarMascota(nombre: String, raza: String, tamano: String, edad: String, fotoUrl: String) {
        this.nombre = nombre
        this.raza = raza
        this.tamaño = tamano
        this.edad = edad
        this.fotoUrl = fotoUrl
    }
}
