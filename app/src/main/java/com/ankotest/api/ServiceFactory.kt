package com.ankotest.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Mukhamed Issa on 5/7/19.
 */
object ServiceFactory {

    private const val ENDPOINT = "https://e04b1e67.ngrok.io"

    val client = OkHttpClient.Builder()
            .addInterceptor {
                val requestBuilder = it.request().newBuilder()
                requestBuilder.addHeader("Content-Type", "application/json")
                requestBuilder.addHeader("Accept", "application/json")
                it.proceed(requestBuilder.build())
            }

    val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client.build())
            .baseUrl(ENDPOINT)
            .build()

    val serviceApi = retrofit.create(BackendDrivenUiApi::class.java)!!
}