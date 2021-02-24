package com.example.petridelivery.abs;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.concurent.DaggerConcurrentComponent;
import com.example.petridelivery.concurent.TaskRunner;
import com.example.petridelivery.util.InjectionUtils;
import com.example.petridelivery.wrappers.WrapperComponent;

import lombok.Getter;

@Getter
public abstract class BaseActivity extends AppCompatActivity {

    protected WrapperComponent wc;
    protected PetriDeliveryApp app;
    protected TaskRunner runner = DaggerConcurrentComponent.create().getTaskRunner();

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(getLayoutId());

        app = (PetriDeliveryApp) getApplication();
        wc = InjectionUtils.getWrapperComponent(app);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    protected abstract int getLayoutId();
}
