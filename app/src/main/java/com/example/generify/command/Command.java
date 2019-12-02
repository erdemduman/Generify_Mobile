package com.example.generify.command;

import com.example.generify.util.GenerifyFunction;

public class Command<T> implements ICommand {

    private GenerifyFunction.ActionT<T> command;
    private boolean canExecuteParam;

    public Command(GenerifyFunction.ActionT<T> command){
        this.command = command;
        this.canExecuteParam = true;
    }

    public Command(GenerifyFunction.ActionT<T> command, boolean canExecuteParam){
        this.command = command;
        this.canExecuteParam = canExecuteParam;
    }

    @Override
    public void execute(Object param) throws UnsupportedOperationException{
        if(canExecute()){
            command.apply((T) param);
        }
        else
            throw new UnsupportedOperationException("Not able to execute");
    }

    @Override
    public boolean canExecute() {
        return canExecuteParam;
    }
}
