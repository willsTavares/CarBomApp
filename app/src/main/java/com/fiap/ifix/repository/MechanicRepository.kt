package com.fiap.ifix.repository

import com.fiap.ifix.api.MechanicWebClient
import com.fiap.ifix.database.dao.MechanicDao
import com.fiap.ifix.model.MechanicItem
import kotlinx.coroutines.flow.Flow

class MechanicRepository(private val dao: MechanicDao, private val webClient: MechanicWebClient) {

    fun searchAll(): Flow<List<MechanicItem>> {
        return dao.getAll()
    }

    suspend fun getAll() {
        webClient.getAllMechanics()?.let { mech ->
            dao.save(mech)
        }
    }
}