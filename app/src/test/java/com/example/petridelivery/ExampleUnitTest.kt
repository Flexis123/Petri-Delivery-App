package com.example.petridelivery

import com.google.gson.Gson
import okhttp3.*
import org.junit.Test
import java.io.IOException
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun getConfig() {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
                .url("http://localhost:8080/config/get_config")
                .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val body = response.body.string()
                val gson = Gson()
                val conf = gson.fromJson(body, Properties::class.java)
            }
        })
    }
}