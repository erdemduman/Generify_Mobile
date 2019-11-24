package com.example.generify.view.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.generify.Command;
import com.example.generify.R;
import com.example.generify.databinding.DefaultTitleBarBinding;

public class TitleBar extends LinearLayout {

    private DefaultTitleBarBinding binding;
    private ImageView leftTitleButtonId;
    private Command command;
    private Object commandParameter;

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.default_title_bar, this, true);

        command = null;
        commandParameter = null;

        initView();
        initListener();

    }

    private void initView(){
        leftTitleButtonId = findViewById(R.id.leftTitleButtonId);
    }

    public void setCommand(Command command){
        this.command = command;
    }

    public void setCommandParameter(Object commandParameter){
        this.commandParameter = commandParameter;
    }

    private void initListener(){
        leftTitleButtonId.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(command.canExecute()){
                    if(commandParameter == null){
                        command.execute();
                    }
                    else{
                        command.execute(commandParameter);
                    }
                }
            }
        });
    }
}
