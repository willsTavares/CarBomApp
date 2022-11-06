package com.fiap.ifix.api

import retrofit2.http.GET
import retrofit2.Call

interface ApiRequests {

    @GET("/Mechanic")
    fun getMechanics(): Call<Mechanic>
}