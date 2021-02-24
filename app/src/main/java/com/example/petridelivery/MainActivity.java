package com.example.petridelivery;

import android.content.Intent;
import android.os.Bundle;

import com.example.petridelivery.abs.BaseActivity;
import com.example.petridelivery.wrappers.base.abs.ApiException;
import com.petri.delivery.web.controllers.abs.IAuthController;
import com.petri.delivery.web.objects.ContDto;
import com.petri.delivery.web.objects.EmployeeType;

public class MainActivity extends BaseActivity {

    IAuthController auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = wc.getAuthWrapper();
        ContDto cont = app.getCont();

        Intent login = new Intent(this, LoginActivity.class);
        if(cont == null){
            startActivity(login);
        }else{
            runner.executeAsync(() -> {
                try{
                    ContDto c = auth.loginWithToken(cont.getAccesToken(), cont.getNumeDeUtilizator());
                    app.setCont(c);

                    Intent i = new Intent();
                    if(c.getTip() == EmployeeType.LIVRARE){
                        i.setClass(this, LivratorActivity.class);
                    }else{
                        i.setClass(this, ManagementActivity.class);
                    }
                    runOnUiThread(() -> startActivity(i));
                }catch (ApiException e){
                    if(e.getStatus() == 401){
                        login.putExtra(getResources().getString(R.string.numeDeUtilizatorExtra), cont.getNumeDeUtilizator());
                        runOnUiThread(() -> startActivity(login));
                    }
                }
            });
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}