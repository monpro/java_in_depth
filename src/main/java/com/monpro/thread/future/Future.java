package com.monpro.thread.future;

public interface Future<V> {
    V get() throws Exception;
}
