package com.example.generify.view.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.generify.command.ICommand;
import com.example.generify.R;
import com.example.generify.databinding.DefaultTitleBarBinding;

public class TitleBar extends LinearLayout {

    private DefaultTitleBarBinding binding;
    private ImageView leftTitleButtonId;
    private ICommand command = null;
    private Object commandParameter= null;

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.default_title_bar, this, true);

        command = binding.getCommand();
        commandParameter = binding.getCommandParameter();

        initView();
        initListener();

    }

    private void initView(){
        leftTitleButtonId = findViewById(R.id.leftTitleButtonId);
    }

    private void initListener(){
        leftTitleButtonId.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    if(command != null)
                        command.execute(commandParameter);
                }catch (UnsupportedOperationException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void setCommand(ICommand command){
        this.command = command;
    }

    public void setCommandParameter(Object commandParameter){
        this.commandParameter = commandParameter;
    }
}
