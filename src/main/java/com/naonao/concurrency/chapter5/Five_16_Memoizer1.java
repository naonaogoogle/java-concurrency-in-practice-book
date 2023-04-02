package com.naonao.concurrency.chapter5;

import com.naonao.concurrency.chapter5.common.Computable;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Five_16_Memoizer1<A, V> implements Computable<A, V> {

    private final Computable<A, V> c;
    private final Map<A, V> cache = new HashMap<>();

    public Five_16_Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}



