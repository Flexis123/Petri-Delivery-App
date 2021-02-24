package com.example.petridelivery.concurent;

import dagger.Component;

@Component(modules = {ConcurrentModule.class})
public interface ConcurrentComponent {
    TaskRunner getTaskRunner();
}
