package com.example.generify.util;

public class GenerifyFunction<T> {

    @FunctionalInterface
    public interface ServiceFunction{
        void apply(GenerifyCallback callback);
    }

    @FunctionalInterface
    public interface Action<T>{
        void apply(T param);
    }
}
