package com.example.retrofitmockworkout.data.mock_api_server

import android.content.Context

class MockDataProvider(private val context: Context) {

    fun read(fileName: String) =
        readFromAsset(fileName)

    private fun readFromAsset(fileName: String) =
        String(readToBuffer(fileName))

    private fun readToBuffer(fileName: String): ByteArray {
        val inputStream = context.assets.open(fileName)

        val buffer = ByteArray(inputStream.available())

        inputStream.read(buffer)
        inputStream.close()

        return buffer
    }
}