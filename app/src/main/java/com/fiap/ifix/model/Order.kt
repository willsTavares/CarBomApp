package com.fiap.ifix.model

data class Order(
    val createdDate: String,
    val formattedDate: String,
    val mechanic: String,
    val name: String,
    val resultCode: String,
    val resultDetails: String
)