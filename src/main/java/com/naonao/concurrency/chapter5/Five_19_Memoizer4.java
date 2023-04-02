package com.naonao.concurrency.chapter5;

import com.naonao.concurrency.chapter5.common.Computable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Five_19_Memoizer4<A,V> implements Computable<A, V> {
    private final Computable<A,V> c;
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    public Five_19_Memoizer4(Computable<A,V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
       while (true) {
              Future<V> f = cache.get(arg);
              if (f == null) {
                FutureTask<V> ft = new FutureTask<>(() -> c.compute(arg));
                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                     f = ft;
                     ft.run();
                }
              }
              try {
                return f.get();
              } catch (Exception e) {
                cache.remove(arg, f);
              }
       }
    }
}
