package com.mars.aidl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Util {

    public static final String TAG = "AIDLMRARS";
    private static volatile ExecutorService mSingleThreadPool;

    private static ExecutorService singleThreadPool() {
        if (isExecutorNotAvailable(mSingleThreadPool)) {
            synchronized (Util.class){
                if (isExecutorNotAvailable(mSingleThreadPool)) {
                    mSingleThreadPool = Executors.newSingleThreadExecutor();
                }
            }
        }
        return mSingleThreadPool;
    }

    private static boolean isExecutorNotAvailable(ExecutorService executorService) {
        return executorService == null || executorService.isShutdown()
                || executorService.isTerminated();
    }

    public static void exeSingleTask(Runnable task) {
        try {
            singleThreadPool().execute(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
