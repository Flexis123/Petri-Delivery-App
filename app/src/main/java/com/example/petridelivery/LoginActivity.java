package com.example.petridelivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.petridelivery.abs.BaseActivity;
import com.example.petridelivery.wrappers.AuthWrapper;
import com.example.petridelivery.wrappers.base.OnResponseCallback;
import com.petri.delivery.web.objects.ContDto;

import javax.inject.Inject;

import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    @Inject
    AuthWrapper auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wc.inject(this);

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

            auth.login(parola, numeDeUtilizator1).enqueue(new OnResponseCallback<ContDto>(getApplicationContext()) {
                @Override
                public void onSuccessful(Response<ContDto> response) {
                    ContDto cont = response.body();
                    app.setCont(cont);

                    runOnUiThread(() -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));
                }

                @Override
                public void onNotSuccesful(Response<ContDto> response) {
                    if(response.code() == 308){
                        Intent changePassword = new Intent(LoginActivity.this, ChangePasswordActivity.class);
                        changePassword.putExtra(numeUtilizator, numeDeUtilizator1);
                        changePassword.putExtra(getResources().getString(R.string.parolaExtra), parola);

                        runOnUiThread(() -> startActivity(changePassword));
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