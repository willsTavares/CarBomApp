package com.fiap.ifix.api

import com.fiap.ifix.model.MechanicItem
import com.fiap.ifix.model.Order
import com.fiap.ifix.model.OrderedService
import com.fiap.ifix.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("OrderedService")
    suspend fun orderedService(
        @Query("userId") userId: String?
    ): List<Order>

    @POST("OrderedService")
    suspend fun postOrderedService(
        @Body orderedService: OrderedService
    )
    :Response<Order>

    @POST("Login/createuser")
    suspend fun createUser(
        @Body user: User
    ): Response<User>

    @POST("Login/authenticate")
    suspend fun authenticateUser(
        @Body user: User
    ): Response<User>

}