package com.example.generify.util;

public class GenerifyFunction{

    @FunctionalInterface
    public interface Action{
        void apply();
    }

    @FunctionalInterface
    public interface ActionT<T>{
        void apply(T param);
    }

    @FunctionalInterface
    public interface FunctionListT<T>{
        void apply();
    }

    @FunctionalInterface
    public interface TFunction<T>{
        T apply();
    }

    @FunctionalInterface
    public interface StringFunction{
        String apply();
    }


}
