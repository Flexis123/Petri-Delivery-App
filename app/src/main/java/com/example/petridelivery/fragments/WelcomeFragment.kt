package com.example.petridelivery.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.petridelivery.R
import com.example.petridelivery.app.PetriDeliveryApp
import com.example.petridelivery.fragments.abs.BaseFragment
import java.util.*

class WelcomeFragment : BaseFragment(R.layout.fragment_welcome) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val welcomeTextView = activity!!.findViewById<TextView>(R.id.welcomeTextView)

        var salutare = ""
        val hour = Calendar.getInstance()[Calendar.HOUR_OF_DAY]

        salutare = if (hour < 12) {
            resources.getString(R.string.dimi)
        } else if (hour == 12) {
            resources.getString(R.string.amiaz)
        } else if (hour < 18) {
            resources.getString(R.string.dupamasa)
        } else {
            resources.getString(R.string.seara)
        }

        welcomeTextView.text = String.format(
                resources.getString(R.string.welcome_msg),
                salutare,
                (activity!!.application as PetriDeliveryApp).getCont()!!.numeDeUtilizator
        )
    }
}