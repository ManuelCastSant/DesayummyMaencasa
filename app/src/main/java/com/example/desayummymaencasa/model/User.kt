package com.example.desayummymaencasa.model


data class User(
    val nombre: String? = null,
    val region: String? = null,
    val ingrediente: String? = null,
    val pais: String? = null,
    val precio: Int? = null
)