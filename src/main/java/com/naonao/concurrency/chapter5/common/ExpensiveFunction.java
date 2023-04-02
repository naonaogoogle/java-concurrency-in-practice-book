package com.naonao.concurrency.chapter5.common;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        // after deep thought...
        return new BigInteger(arg);
    }
}