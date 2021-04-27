package com.example.petridelivery

import android.os.Bundle
import com.example.petridelivery.abs.BaseActivity

class LivratorActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_livrator
    }
}