package com.example.petridelivery;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petridelivery.abs.BaseActivity;
import com.example.petridelivery.wrappers.AuthWrapper;
import com.example.petridelivery.wrappers.base.OnResponseCallback;

import javax.inject.Inject;

import retrofit2.Response;

public class ChangePasswordActivity extends BaseActivity {

    @Inject
    AuthWrapper auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wc.inject(this);

        Intent intent = getIntent();
        Resources res = getResources();

        findViewById(R.id.confirmPasswordChangeButton).setOnClickListener(v -> {
            String newPassword = ((EditText)findViewById(R.id.newPasswordEditText))
                    .getText().toString();

            String newPasswordRepeated = ((EditText) findViewById(R.id.newPasswordRepeatedEditText))
                    .getText().toString();

            if(newPassword.equals(newPasswordRepeated)){
                String numeDeUtilizator = intent.getStringExtra(res.getString(R.string.numeDeUtilizatorExtra));

                auth.change_default_password(
                        intent.getStringExtra(res.getString(R.string.parolaExtra)),
                        newPassword,
                        numeDeUtilizator
                ).enqueue(new OnResponseCallback<Void>(getApplicationContext()) {
                    @Override
                    public void onSuccessful(Response<Void> response) {
                        Intent login = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                        login.putExtra(res.getString(R.string.numeDeUtilizatorExtra), numeDeUtilizator);
                        startActivity(login);
                    }
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