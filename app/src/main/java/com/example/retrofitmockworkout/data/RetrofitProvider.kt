package com.example.retrofitmockworkout.data

import com.example.retrofitmockworkout.BuildConfig
import com.example.retrofitmockworkout.data.mock_api_server.MockApiInterceptor
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    private val loggingInterceptor = HttpLoggingInterceptor()
    private val mockInterceptor = MockApiInterceptor()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(mockInterceptor)
        .build()

    private val retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.MOCK_CARS_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    fun provideApi(): CarsAPI =
        retrofit.create(CarsAPI::class.java)
}