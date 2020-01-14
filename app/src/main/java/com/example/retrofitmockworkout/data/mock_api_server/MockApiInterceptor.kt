package com.example.retrofitmockworkout.data.mock_api_server

import com.example.retrofitmockworkout.BuildConfig
import okhttp3.*
import okhttp3.Interceptor.Chain

class MockApiInterceptor : Interceptor {

    override fun intercept(chain: Chain): Response =
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()

            val response = if (uri.contains(Regex("[0-9]"))) {
                mockCar1
            } else {
                mockCars
            }

            chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(response)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        response.toByteArray()
                    )
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            chain.proceed(chain.request()).newBuilder().build()
        }
}

private val mockCars = """
    [
        {
            "id": 1,
            "brand": "Mercedes-Benz",
            "model": "E 220",
            "year": 2016,
            "color": "Dark blue",
            "cost": "35000 euro"
        },
        {
            "id": 2,
            "brand": "Volkswagen",
            "model": "Touareg",
            "year": 2015,
            "color": "Grey Canyon",
            "cost": "39000 euro"
        }
    ]
""".trimIndent()

private val mockCar1 = """
    {
        "id": 1,
        "brand": "Mercedes-Benz",
        "model": "E 220",
        "year": 2016,
        "color": "Dark blue",
        "cost": "35000 euro"
    }
""".trimIndent()