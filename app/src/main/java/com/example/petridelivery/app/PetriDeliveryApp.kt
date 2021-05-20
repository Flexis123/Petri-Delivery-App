package com.example.petridelivery.app

import android.app.Application
import com.example.petridelivery.wrappers.base.abs.File
import com.example.petridelivery.wrappers.base.abs.JsonMapper
import com.petri.delivery.web.objects.ContDto
import java.util.*
import javax.inject.Inject

class PetriDeliveryApp : Application() {
    private var cont: ContDto? = null
    private var configuration: Hashtable<String?, Any?>? = null

    @kotlin.jvm.JvmField
    @Inject
    var contFile: File? = null

    @kotlin.jvm.JvmField
    @Inject
    var mapper: JsonMapper? = null

    fun getCont(): ContDto? {
        return mapper!!.fromJson(contFile!!, ContDto::class.java)
    }

    fun setCont(cont: ContDto?) {
        mapper!!.toJson(contFile!!, cont)
        this.cont = cont
    }

    fun getConfiguration(): Hashtable<String?, Any?>? {
        return configuration
    }

    fun setConfiguration(configuration: Hashtable<String?, Any?>?) {
        this.configuration = configuration
    }
}