package com.fiap.ifix.repository

import com.fiap.ifix.api.MechanicWebClient
import com.fiap.ifix.model.MechanicItem

class MechanicRepository(private val webClient: MechanicWebClient) {


    suspend fun getAll(id: String?, name: String?, UserLatitude: Double?, UserLongitude: Double?, Services: String?): List<MechanicItem>? {
        return webClient.getAllMechanics(id, name, UserLatitude, UserLongitude, Services)
    }
}