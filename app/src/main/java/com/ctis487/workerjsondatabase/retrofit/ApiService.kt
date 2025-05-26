package com.ctis487.workerjsondatabase.retrofit

import com.ctis487.workerjsondatabase.model.Cars
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("cars")
    fun getCars(): Call<List<Cars>>
}