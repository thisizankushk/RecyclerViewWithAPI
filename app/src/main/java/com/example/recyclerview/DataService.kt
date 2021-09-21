package com.example.recyclerview

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "http://dummy.restapiexample.com/api/v1/"

interface dataInterface {


    @GET("employees")
    fun getstatedata(): Call<Info>


}

object dataservice {
    val datainstance: dataInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        datainstance = retrofit.create(dataInterface::class.java)

    }

}