package com.naonao.concurrency.chapter6;

import java.util.concurrent.Executor;

public class Six_6_WithInThreadExecutor implements Executor {

    @Override
    public void execute(Runnable r) {
        r.run();
    }
}
