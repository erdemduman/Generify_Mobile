package com.example.generify.util;

public class GenerifyFunction {

    @FunctionalInterface
    public interface ServiceFunction{
        void apply(GenerifyCallback callback);
    }
}
