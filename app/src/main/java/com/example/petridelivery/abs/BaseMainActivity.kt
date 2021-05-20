package com.example.petridelivery.abs

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.petridelivery.LoginActivity
import com.example.petridelivery.R
import com.example.petridelivery.fragments.WelcomeFragment
import com.example.petridelivery.fragments.abs.BaseFragment
import com.google.android.material.navigation.NavigationView

abstract class BaseMainActivity(private val drawerResId: Int, private val toolbarResId: Int, private val fragmentHolderResId: Int, private val menuResId: Int) : BaseActivity() {
    protected lateinit var toggle: ActionBarDrawerToggle
    protected lateinit var dr: DrawerLayout
    protected lateinit var menu: Menu
    protected val LOGIN_GROUP = 0

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        val toolbar = findViewById<Toolbar?>(toolbarResId)
        setSupportActionBar(toolbar)

        dr = findViewById(drawerResId)
        toggle = ActionBarDrawerToggle(this, dr, toolbar, R.string.deschide, R.string.inchide)
        dr.addDrawerListener(toggle)
        toggle.syncState()

        menu = (findViewById<View>(menuResId) as NavigationView)
                .getMenu()

        val subMenu = menu.addSubMenu(Menu.NONE, 9, 2, "")
        val logOut = subMenu.add(LOGIN_GROUP, 3, 1, R.string.log_out)

        logOut.setOnMenuItemClickListener { item: MenuItem? ->
            app.setCont(null)
            startActivity(Intent(this, LoginActivity::class.java))
            true
        }

        subMenu.setGroupCheckable(LOGIN_GROUP, true, true)
        setupMenu()
        changeFragment(WelcomeFragment())
    }

    protected fun changeFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
                .replace(fragmentHolderResId, fragment)
                .commit()
    }

    abstract fun setupMenu()

}