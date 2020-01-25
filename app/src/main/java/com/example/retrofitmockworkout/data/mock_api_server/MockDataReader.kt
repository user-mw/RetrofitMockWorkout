package com.example.retrofitmockworkout.data.mock_api_server

import android.content.Context

class MockDataReader(private val context: Context) {

    fun read(fileName: String): String =
        readFromAsset(fileName)

    private fun readFromAsset(fileName: String): String =
        String(readToBuffer(fileName))

    private fun readToBuffer(fileName: String): ByteArray {
        val inputStream = context.assets.open(fileName)

        val buffer = ByteArray(inputStream.available())

        inputStream.read(buffer)
        inputStream.close()

        return buffer
    }
}