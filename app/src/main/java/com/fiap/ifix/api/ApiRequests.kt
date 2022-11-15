package com.fiap.ifix.api

import com.fiap.ifix.model.MechanicItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests {

    @GET("/Mechanic")
    suspend fun getMechanics(
        @Query("id") id: String?,
        @Query("name") name: String?,
        @Query("UserLatitude") lat: Double?,
        @Query("UserLongitude") long: Double?,
        @Query("Services") service: String?,
    ): List<MechanicItem>


}