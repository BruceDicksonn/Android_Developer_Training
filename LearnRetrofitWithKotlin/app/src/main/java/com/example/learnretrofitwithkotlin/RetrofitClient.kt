package com.example.learnretrofitwithkotlin

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private lateinit var INSTANCE: Retrofit
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        private fun getInstance():Retrofit {
            if(!::INSTANCE.isInitialized){
                val http = OkHttpClient.Builder().build();
                INSTANCE = Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .client(http)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return INSTANCE
        }

        fun <T> getService(abc: Class<T>) : T {
            return getInstance().create(abc)
        }

    }

}