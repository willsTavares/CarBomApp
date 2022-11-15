package com.fiap.ifix.api

import android.util.Log
import com.fiap.ifix.model.MechanicItem

class MechanicWebClient {

    suspend fun getAllMechanics(): List<MechanicItem>? {

      return try {
            val mechanicsResponse =  RetrofitInitializer().mechanicService.getMechanics()
            return mechanicsResponse.map { mech ->
                mech
            }
        } catch (e: Exception) {
            Log.e("Mechanic", "getAllMechanics: $e" )
            null
        }
    }

}