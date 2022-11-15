package com.fiap.ifix.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class MechanicItem(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val address: Address?,
    val description: String?,
    val distance: Double?,
    val image: Any?,
    val name: String?,
    val ranking: Double?,
    val services: List<Service>?
){
    val mechanic get() = MechanicItem(
        description = description ?: "",
        distance = distance ?: 0.0,
        image = image ?: "",
        name = name ?: "",
        ranking = ranking ?: 0.0,
        id = id ?: "0",
        address = address,
        services = services
    )
}