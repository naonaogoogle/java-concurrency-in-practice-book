package com.naonao.concurrency.chapter6;

import java.util.concurrent.Executor;

public class Six_5_ThreadPerTaskExecutor implements Executor {

    @Override
    public void execute(Runnable r) {
        new Thread(r).start();
    }
}
