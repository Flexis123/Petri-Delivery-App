package com.example.petridelivery;

import android.os.Bundle;

import com.example.petridelivery.abs.BaseMainActivity;
import com.petri.delivery.web.objects.EmployeeType;

public class ManagementActivity extends BaseMainActivity {

    public ManagementActivity() {
        super(R.id.management_drawer_layout, R.id.management_toolbar,
                R.id.management_fragment_container, R.id.management_nav_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(app.getCont().getTip() == EmployeeType.MANAGER){
            this.menu.removeItem(R.id.angajatiItem);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_management;
    }
}