package com.fiap.ifix.repository

import com.fiap.ifix.api.MechanicWebClient
import com.fiap.ifix.model.MechanicItem
import com.fiap.ifix.model.Order
import com.fiap.ifix.model.OrderedService
import retrofit2.Response

class MechanicRepository(private val webClient: MechanicWebClient) {


    suspend fun getAll(id: String?, name: String?, UserLatitude: Double?, UserLongitude: Double?, Services: String?): List<MechanicItem>? {
        return webClient.getAllMechanics(id, name, UserLatitude, UserLongitude, Services)
    }

    suspend fun getService( userId: String?): List<Order>? {
        return webClient.getAllServicesById(userId )
    }

    suspend fun postService(mechanicId: String?, serviceId: String?, userId: String?): Response<Order>? {
        val orderedService = OrderedService(mechanicId, serviceId, userId)
        return webClient.postOrderedService(orderedService)
    }
}