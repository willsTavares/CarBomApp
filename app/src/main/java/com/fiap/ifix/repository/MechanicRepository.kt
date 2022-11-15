package com.fiap.ifix.repository

import com.fiap.ifix.api.MechanicWebClient
import com.fiap.ifix.model.MechanicItem

class MechanicRepository(private val webClient: MechanicWebClient) {

    suspend fun getAll(): List<MechanicItem>? {
        return webClient.getAllMechanics()
    }
}