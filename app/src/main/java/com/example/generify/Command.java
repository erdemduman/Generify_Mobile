package com.example.generify;

public interface Command{
    void execute();
    void execute(Object param);
    boolean canExecute();
}
