package com.fiap.ifix.repository

import android.util.Log
import com.fiap.ifix.api.RetrofitInitializer
import com.fiap.ifix.model.User
import retrofit2.Response

class UserRepository {

    suspend fun createUser(email: String, name: String, password: String): Response<User>?{
        return try {
            val user = User(email, name, password)
            RetrofitInitializer().mechanicService.createUser(user)

        } catch (e: Exception){
            Log.i("Error", e.toString())
            null
        }
    }


    suspend fun authenticateUser(email: String, password: String): Response<User>?{
        return try {
            val user = User(email, null, password)
            RetrofitInitializer().mechanicService.authenticateUser(user)

        } catch (e: Exception){
            Log.i("Error", e.toString())
            null
        }
    }
}

