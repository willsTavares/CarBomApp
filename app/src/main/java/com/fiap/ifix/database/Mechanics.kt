package com.fiap.ifix.database

import java.util.*


data class Mechanics(
    var id: Long,
    var name: String,
    var image: String,
    var ranking: Double,
    var istance: Double,
    var serviceTypes: Objects,
    var address: Address,
)
