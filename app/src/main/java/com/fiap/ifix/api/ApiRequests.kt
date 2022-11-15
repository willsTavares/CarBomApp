package com.fiap.ifix.api

import com.fiap.ifix.model.MechanicItem
import retrofit2.http.GET

interface ApiRequests {

    @GET("/Mechanic")
    suspend fun getMechanics(): List<MechanicItem>


}