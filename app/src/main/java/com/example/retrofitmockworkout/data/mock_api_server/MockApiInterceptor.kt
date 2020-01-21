package com.example.retrofitmockworkout.data.mock_api_server

import com.example.retrofitmockworkout.BuildConfig
import okhttp3.*
import okhttp3.Interceptor.Chain

class MockApiInterceptor(private val dataProvider: MockDataProvider) : Interceptor {

    private companion object {
        const val WRONG_ID = -1
    }

    override fun intercept(chain: Chain): Response =
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()

            val id = getId(uri)

            val response = if (id != WRONG_ID) {
                dataProvider.read("car_$id.json")
            } else {
                dataProvider.read("cars.json")
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

    private fun getId(uriString: String): Int {
        val chars = uriString.toCharArray()

        return if (chars.last().isDigit()) {
            chars.last().toString().toInt()
        } else {
            WRONG_ID
        }
    }
}