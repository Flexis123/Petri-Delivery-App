package com.example.petridelivery.concurent;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class TaskRunner {
    private final Executor executor;

    @Inject
    public TaskRunner(Executor executor) {
        this.executor = executor;
    }

    public <R> void executeAsync(Runnable runnable){
        executor.execute(() -> {
            runnable.run();
        });
    }
}