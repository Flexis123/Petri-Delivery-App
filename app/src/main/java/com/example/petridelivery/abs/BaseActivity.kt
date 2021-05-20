package com.example.petridelivery.abs

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petridelivery.app.PetriDeliveryApp
import com.example.petridelivery.util.getWrapperComponent
import com.example.petridelivery.wrappers.WrapperComponent


abstract class BaseActivity : AppCompatActivity() {
    lateinit var wc: WrapperComponent
    lateinit var app: PetriDeliveryApp

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setContentView(getLayoutId())

        app = application as PetriDeliveryApp
        wc = getWrapperComponent(app)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    protected abstract fun getLayoutId(): Int

}