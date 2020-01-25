package com.example.retrofitmockworkout.data.mock_api_server

import okhttp3.*

private const val MESSAGE = "OK"
private const val WRONG_ID = -1

private val Interceptor.Chain.uriString: String
    get() = this.request().url().uri().toString()

fun Interceptor.Chain.getMockResponse(mockDataReader: MockDataReader, code: Int): Response{
    val jsonMockContent = getJsonData(uriString, mockDataReader)

    return Response.Builder()
        .request(request())
        .protocol(Protocol.HTTP_2)
        .code(code)
        .message(MESSAGE)
        .addHeader("content-type", "application/json")
        .body(
            ResponseBody.create(
                MediaType.parse("application/json"),
                jsonMockContent
            )
        )
        .build()
}

private fun getJsonData(uriString: String, mockDataReader: MockDataReader): String {
    val id = uriString.getIdFromUri()

    return if (id != WRONG_ID) {
        mockDataReader.read("car_$id.json")
    } else {
        mockDataReader.read("cars.json")
    }
}

private fun String.getIdFromUri(): Int {
    val chars = this.toCharArray()

    return if (chars.last().isDigit()) {
        chars.last().toString().toInt()
    } else {
        WRONG_ID
    }
}
