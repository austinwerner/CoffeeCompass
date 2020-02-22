package com.werner.coffeecompass.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutor {

    private static AppExecutor instance;

    public static AppExecutor get() {

        if (instance == null) {
            instance = new AppExecutor();
        }
        return instance;
    }

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO() {

        return mNetworkIO;
    }
}