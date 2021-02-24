package com.example.petridelivery.concurent;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;

@Module
public class ConcurrentModule {

    @Provides
    public Executor getMainExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    public Handler getMainHandler(){
        return new Handler(Looper.getMainLooper());
    }
}
