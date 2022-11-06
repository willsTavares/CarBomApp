package com.fiap.ifix.database

data class Address(
    var state: String,
    var city: String,
    var street: String,
    var number: String,
    var zipPostalCode: Long,
    var neighbourhood: String,
    var latitude: Long,
    var longitude: Long,
)
