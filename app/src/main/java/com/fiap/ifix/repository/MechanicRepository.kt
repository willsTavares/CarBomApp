package com.fiap.ifix.repository

import android.content.Context
import android.util.Log
import com.fiap.ifix.api.MechanicWebClient
import com.fiap.ifix.model.MechanicItem

class MechanicRepository(private val webClient: MechanicWebClient) {

    private lateinit var context: Context

    suspend fun getAll(id: String?, name: String?, UserLatitude: Double?, UserLongitude: Double?, Services: String?): List<MechanicItem>? {
        return webClient.getAllMechanics(id, name, UserLatitude, UserLongitude, Services)
    }
}