package com.example.petridelivery.wrappers.base

import com.example.petridelivery.app.PetriDeliveryApp
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*
import javax.inject.Inject

class AuthenticatedRequestInterceptor @Inject constructor(private val app: PetriDeliveryApp?) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val config = app!!.getConfiguration()

        if (config != null) {
            if ((config["authFilterList"] as MutableList<String>).contains(request.url.toUri().toString())) {
                val cont = app.getCont()
                val filters = config["filters"] as Hashtable<String, String>

                val tokenHeader = filters[TOKEN]
                val usernameHeader = filters[USERNAME]

                request = request.newBuilder()
                        .addHeader(tokenHeader!!, cont!!.accesToken)
                        .addHeader(usernameHeader!!, cont.numeDeUtilizator)
                        .build()
            }
        }
        return chain.proceed(request)
    }

    companion object {
        val TOKEN: String? = "filter.access.header.username"
        val USERNAME: String? = "filter.access.header.token"
    }
}