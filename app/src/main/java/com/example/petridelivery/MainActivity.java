package com.example.petridelivery;

import android.content.Intent;
import android.os.Bundle;

import com.example.petridelivery.abs.BaseActivity;
import com.example.petridelivery.wrappers.AuthWrapper;
import com.example.petridelivery.wrappers.ConfigWrapper;
import com.example.petridelivery.wrappers.base.OnResponseCallback;
import com.petri.delivery.web.objects.ContDto;
import com.petri.delivery.web.objects.EmployeeType;

import java.util.Hashtable;

import javax.inject.Inject;

import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @Inject
    AuthWrapper auth;

    @Inject
    ConfigWrapper config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wc.inject(this);

        ContDto cont = app.getCont();

        Intent login = new Intent(this, LoginActivity.class);
        if(cont == null){
            startActivity(login);
        }else{
            auth.loginWithToken(cont.getAccesToken(), cont.getNumeDeUtilizator()).enqueue(new OnResponseCallback<ContDto>(getApplicationContext()) {

                @Override
                public void onSuccessful(Response<ContDto> response) {
                    ContDto c = response.body();
                    app.setCont(c);

                    config.getConfig().enqueue(new OnResponseCallback<Hashtable<String, Object>>(getApplicationContext()) {
                        @Override
                        public void onSuccessful(Response<Hashtable<String, Object>> response) {
                            app.setConfiguration(response.body());

                            Intent i = new Intent();
                            if(c.getTip() == EmployeeType.LIVRARE){
                                i.setClass(MainActivity.this, LivratorActivity.class);
                            }else{
                                i.setClass(MainActivity.this, ManagementActivity.class);
                            }
                            runOnUiThread(() -> startActivity(i));
                        }
                    });
                }

                @Override
                public void onNotSuccesful(Response<ContDto> response) {
                    if(response.code() == 401){
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