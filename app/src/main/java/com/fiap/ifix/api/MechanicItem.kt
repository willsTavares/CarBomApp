package com.fiap.ifix.api

data class MechanicItem(
    val address: Address,
    val distance: Double,
    val id: String,
    val image: String,
    val name: String,
    val ranking: Double,
    val serviceTypes: List<String>
)