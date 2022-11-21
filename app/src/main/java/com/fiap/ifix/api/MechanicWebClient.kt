package com.fiap.ifix.api

import android.util.Log
import androidx.room.Index
import com.fiap.ifix.model.MechanicItem
import com.fiap.ifix.model.Order
import com.fiap.ifix.model.OrderedService
import retrofit2.Response
import retrofit2.http.Query

class MechanicWebClient {

    suspend fun getAllMechanics(id: String?, name: String?, UserLatitude: Double?, UserLongitude: Double?, Services: String? ): List<MechanicItem>? {

      return try {
            val mechanicsResponse =  RetrofitInitializer().mechanicService.getMechanics(id, name, UserLatitude, UserLongitude, Services)
            return mechanicsResponse.map { mech ->
                mech
            }
        } catch (e: Exception) {
            Log.e("Mechanic", "getAllMechanics: $e" )
            null
        }
    }

    suspend fun getAllServicesById(userId: String?): List<Order>? {
        return try {
            val mechanicService = RetrofitInitializer().mechanicService.orderedService(userId)
            return mechanicService.map {mech ->
                mech
            }
        } catch (e: Exception) {
            Log.e("Mechanic Service", "getAllServices: $e")
            null
        }
    }
    suspend fun postOrderedService(orderedService: OrderedService): Response<Order>? {
        return try {
            RetrofitInitializer().mechanicService.postOrderedService(orderedService)
        }catch (e: Exception) {
            Log.e("Post Mechanic Service", "send service: $e")
            null
        }
    }


}