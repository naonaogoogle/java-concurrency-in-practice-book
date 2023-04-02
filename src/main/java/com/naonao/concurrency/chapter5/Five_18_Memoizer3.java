package com.naonao.concurrency.chapter5;

import com.naonao.concurrency.chapter5.common.Computable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Five_18_Memoizer3<A,V> implements Computable<A, V> {
    private final Computable<A,V> c;
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    public Five_18_Memoizer3(Computable<A,V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            FutureTask<V> ft = new FutureTask<>(() -> c.compute(arg));
            f = ft;
            cache.put(arg, ft);
            ft.run();// in this step, call c.compute(arg)
        }
        try {
            return f.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
