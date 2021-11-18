package com.example.recyclerview_firestore

data class Productos(
    var nombre: String? = null,
    var imagenUrl: String? = null,
    var precio: String? = null,
    var descripcion: String? = null,
    var oferta: String? = null
)
