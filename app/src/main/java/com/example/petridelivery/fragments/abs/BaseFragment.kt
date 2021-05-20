package com.example.petridelivery.fragments.abs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.petridelivery.abs.BaseMainActivity
import com.example.petridelivery.app.PetriDeliveryApp
import com.example.petridelivery.wrappers.WrapperComponent

abstract class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {
    protected lateinit var wc: WrapperComponent
    protected lateinit var app: PetriDeliveryApp
    protected lateinit var activity: BaseMainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    protected fun setup() {
        activity = getActivity() as BaseMainActivity
        wc = activity.wc
        app = activity.app
    }
}