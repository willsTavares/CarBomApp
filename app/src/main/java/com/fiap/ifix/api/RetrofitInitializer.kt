package com.fiap.ifix.api

import com.fiap.ifix.presentation.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInitializer {

        private val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val mechanicService = api.create(ApiRequests::class.java)


}