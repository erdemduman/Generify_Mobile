package com.example.generify.command;

public interface ICommand {
    void execute(Object param) throws UnsupportedOperationException;
    boolean canExecute();
}
