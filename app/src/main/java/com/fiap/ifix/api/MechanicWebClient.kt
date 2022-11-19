package com.fiap.ifix.api

import android.util.Log
import com.fiap.ifix.model.MechanicItem
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

}