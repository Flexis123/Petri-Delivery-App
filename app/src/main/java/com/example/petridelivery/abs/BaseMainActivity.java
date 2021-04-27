package com.example.petridelivery.abs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.petridelivery.LoginActivity;
import com.example.petridelivery.R;
import com.example.petridelivery.fragments.WelcomeFragment;
import com.example.petridelivery.fragments.abs.BaseFragment;
import com.google.android.material.navigation.NavigationView;

public abstract class BaseMainActivity extends BaseActivity{

    protected ActionBarDrawerToggle toggle;
    protected DrawerLayout dr;
    protected Menu menu;
    protected FrameLayout fragmentHolder;
    protected final int LOGIN_GROUP = 0;

    private final int drawerResId;
    private final int toolbarResId;
    private final int fragmentHolderResId;
    private final int menuResId;

    public BaseMainActivity(int drawerResId, int toolbarResId, int fragmentHolderResId, int menuResId) {
        this.drawerResId = drawerResId;
        this.toolbarResId = toolbarResId;
        this.fragmentHolderResId = fragmentHolderResId;
        this.menuResId = menuResId;
    }

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        Toolbar toolbar = findViewById(toolbarResId);
        setSupportActionBar(toolbar);

        dr = findViewById(drawerResId);

        toggle = new ActionBarDrawerToggle(this, dr, toolbar, R.string.deschide, R.string.inchide);
        dr.addDrawerListener(toggle);
        toggle.syncState();

        this.menu = ((NavigationView) findViewById(menuResId))
                .getMenu();

        SubMenu subMenu = menu.addSubMenu(Menu.NONE, 9, 2, "");

        MenuItem logOut = subMenu.add(LOGIN_GROUP, 3, 1, R.string.log_out);
        logOut.setOnMenuItemClickListener(item -> {
            app.setCont(null);
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        });

        subMenu.setGroupCheckable(LOGIN_GROUP, true, true);
        setupMenu();
        changeFragment(new WelcomeFragment());
    }

    protected void changeFragment(BaseFragment fragment){
        getSupportFragmentManager().beginTransaction()
            .replace(fragmentHolderResId, fragment)
                .commit();
    }

    public abstract void setupMenu();
}
