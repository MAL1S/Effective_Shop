package ru.malis.core_network.clients

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.malis.core_network.api.ProductApi
import java.util.concurrent.TimeUnit

object ProductClient {

    private const val BASE_URL = "https://run.mocky.io/v3/"

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .build()
    }

    private fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Synchronized
    fun buildProductApi(): ProductApi = getRetrofitClient().create(ProductApi::class.java)
}