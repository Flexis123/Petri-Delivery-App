package com.example.petridelivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.petridelivery.abs.BaseActivity;
import com.example.petridelivery.wrappers.base.abs.ApiException;
import com.petri.delivery.web.controllers.abs.IAuthController;
import com.petri.delivery.web.objects.ContDto;

public class LoginActivity extends BaseActivity {

    IAuthController auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = wc.getAuthWrapper();

        String numeUtilizator = getResources().getString(R.string.numeDeUtilizatorExtra);

        Intent intent = getIntent();
        String numeDeUtilizator = intent.getStringExtra(numeUtilizator);
        EditText numeDeUtilizatorEditText = findViewById(R.id.numeDeUtilizatorEditText);

        if(numeDeUtilizator != null){
            numeDeUtilizatorEditText.setText(numeDeUtilizator);
        }

        findViewById(R.id.loginButton).setOnClickListener(v -> {
            String usr = numeDeUtilizatorEditText.getText().toString();
            String numeDeUtilizator1 = usr.equals(numeDeUtilizator) ? numeDeUtilizator : usr;

            String parola = ((EditText)findViewById(R.id.parolaEditText))
                    .getText().toString();

            runner.executeAsync(() -> {
                try{
                    ContDto cont = auth.login(parola, numeDeUtilizator1);
                    app.setCont(cont);

                    this.runOnUiThread(() -> startActivity(new Intent(this, MainActivity.class)));
                }catch (ApiException e){
                    if(e.getStatus() == 308){
                        Intent changePassword = new Intent(this, ChangePasswordActivity.class);
                        changePassword.putExtra(numeUtilizator, numeDeUtilizator1);
                        changePassword.putExtra(getResources().getString(R.string.parolaExtra), parola);

                        this.runOnUiThread(() -> startActivity(changePassword));
                    }
                }
            });
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}