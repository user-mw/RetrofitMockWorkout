package com.example.retrofitmockworkout.data.mock_api_server

import com.example.retrofitmockworkout.BuildConfig
import okhttp3.*
import okhttp3.Interceptor.Chain

class MockApiInterceptor(private val mockDataReader: MockDataReader) : Interceptor {

    private companion object {
        const val OK_CODE = 200
    }

    override fun intercept(chain: Chain): Response =
        if (BuildConfig.DEBUG) {
            chain.getMockResponse(code = OK_CODE, mockDataReader = mockDataReader)
        } else {
            chain.proceed(chain.request()).newBuilder().build()
        }
}