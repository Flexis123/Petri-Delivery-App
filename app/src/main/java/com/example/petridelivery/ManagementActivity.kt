package com.example.petridelivery

import android.os.Bundle
import android.view.MenuItem
import com.example.petridelivery.abs.BaseMainActivity
import com.example.petridelivery.fragments.ClientiFragment
import com.petri.delivery.web.objects.EmployeeType

class ManagementActivity : BaseMainActivity(R.id.management_drawer_layout, R.id.management_toolbar,
        R.id.management_fragment_container, R.id.management_nav_view) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (app.getCont()!!.tip == EmployeeType.MANAGER) {
            menu.removeItem(R.id.angajatiItem)
        }
    }

    override fun setupMenu() {
        menu.findItem(R.id.clientiItem).setOnMenuItemClickListener { item: MenuItem? ->
            changeFragment(ClientiFragment())
            true
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_management
    }
}