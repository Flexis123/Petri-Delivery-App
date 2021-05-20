package com.example.petridelivery.wrappers.base

import android.os.Looper
import android.widget.Toast
import com.example.petridelivery.app.PetriDeliveryApp
import com.example.petridelivery.wrappers.base.abs.JsonMapper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class UnsuccesfulRequestInterceptor @Inject constructor(private val jsonMapper: JsonMapper, private val app: PetriDeliveryApp) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val res = chain.proceed(chain.request())
        if (chain.request().url.toUri().toString().contains("config")) {
            println()
        }
        if (!res.isSuccessful) {
            val body = res.peekBody(1024 * 4.toLong()).string()
            var text = ""
            try {
                for (constraint in jsonMapper.fromJson(body, MutableList::class.java)!!) {
                    text += constraint.toString() + "\n"
                }
            } catch (e: Throwable) {
                text = body
            }
            try {
                Looper.prepare()
            } catch (e: RuntimeException) {
            }
            Toast.makeText(app, text, Toast.LENGTH_LONG).show()
        }
        return res
    }

}