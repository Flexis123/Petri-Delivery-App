package com.example.petridelivery.wrappers.base

import android.content.Context
import com.example.petridelivery.app.PetriDeliveryApp
import com.example.petridelivery.wrappers.base.abs.File
import com.example.petridelivery.wrappers.base.abs.JsonMapper
import com.google.gson.Gson
import java.io.*

class GsonJsonMapper(private val app: PetriDeliveryApp) : JsonMapper {
    private val m: Gson = Gson()

    override fun <T> fromJson(json: String?, cls: Class<T>): T? {
        return m.fromJson(json, cls)
    }

    override fun toJson(obj: Any?): String {
        return m.toJson(obj)
    }

    override fun <T> fromJson(f: File, cls: Class<T>): T? {
        return try {
            val `in`: InputStream? = app.openFileInput(f.toString())
            val reader = InputStreamReader(`in`)
            m.fromJson(reader, cls)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    override fun toJson(f: File, obj: Any?) {
        try {
            val out: OutputStream? = app.openFileOutput(f.toString(), Context.MODE_PRIVATE)
            try {
                OutputStreamWriter(out).use { writer ->
                    val json = toJson(obj)
                    writer.write(json)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

}