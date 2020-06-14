package com.monpro.thread.Future;

public interface Future<V> {
    V get() throws Exception;
}
