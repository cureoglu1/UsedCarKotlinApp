package com.ctis487.workerjsondatabase.retrofit

import com.ctis487.workerjsondatabase.util.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//STEP1
object ApiClient {

    private const val BASE_URL = "https://freetestapi.com/api/v1/"  // Note: we remove "cars" as it's the endpoint

    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}