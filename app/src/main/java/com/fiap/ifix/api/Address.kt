package com.fiap.ifix.api

data class Address(
    val city: String,
    val latitude: Float,
    val longitude: Float,
    val neighbourhood: String,
    val number: String,
    val state: String,
    val street: String,
    val zipPostalCode: String
)