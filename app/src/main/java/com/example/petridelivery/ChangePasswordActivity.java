package com.example.petridelivery;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petridelivery.abs.BaseActivity;
import com.example.petridelivery.wrappers.base.abs.ApiException;
import com.petri.delivery.web.controllers.abs.IAuthController;

public class ChangePasswordActivity extends BaseActivity {

    IAuthController auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = wc.getAuthWrapper();

        Intent intent = getIntent();
        Resources res = getResources();

        findViewById(R.id.confirmPasswordChangeButton).setOnClickListener(v -> {
            String newPassword = ((EditText)findViewById(R.id.newPasswordEditText))
                    .getText().toString();

            String newPasswordRepeated = ((EditText) findViewById(R.id.newPasswordRepeatedEditText))
                    .getText().toString();

            if(newPassword.equals(newPasswordRepeated)){
                runner.executeAsync(() -> {
                    try{
                        String numeDeUtilizator = intent.getStringExtra(res.getString(R.string.numeDeUtilizatorExtra));
                        auth.change_default_password(
                                intent.getStringExtra(res.getString(R.string.parolaExtra)),
                                newPassword,
                                numeDeUtilizator
                        );

                        Intent login = new Intent(this, LoginActivity.class);
                        login.putExtra(res.getString(R.string.numeDeUtilizatorExtra), numeDeUtilizator);
                        startActivity(login);
                    }catch (ApiException e){}
                });
            }else if (newPassword.isEmpty()){
                Toast.makeText(this, R.string.parola_e_goala, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, R.string.parole_diferite, Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.inapoiLaLoginBtn)
                .setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password;
    }


}