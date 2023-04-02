package com.naonao.concurrency.chapter6;

import java.util.Timer;
import java.util.TimerTask;

public class Six_9_OutOfTime {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.schedule(new ThrowTask(), 1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class ThrowTask extends TimerTask {
        @Override
        public void run() {
            throw new RuntimeException();
        }
    }
}
