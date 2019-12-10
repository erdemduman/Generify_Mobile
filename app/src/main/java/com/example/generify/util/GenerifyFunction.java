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
    public interface StrFunction{
        String apply();
    }

    @FunctionalInterface
    public interface StrFunctionStr{
        String apply(String str);
    }

    @FunctionalInterface
    public interface StrFunctionStrStrArr{
        String apply(String str, String[] param);
    }
}
