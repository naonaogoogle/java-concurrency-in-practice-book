package com.naonao.concurrency.chapter5.common;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}