package com.example.generify.view.customView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.generify.command.ICommand;
import com.example.generify.R;
import com.example.generify.databinding.DefaultTitleBarBinding;

public class TitleBar extends LinearLayout {

    private DefaultTitleBarBinding binding;

    //region Components

    private ImageView title_left_icon;
    private ImageView title_right_icon;
    private TextView title_name;

    //endregion

    //region Properties

    private String name;
    private Drawable leftIcon;
    private Drawable rightIcon;
    private ICommand leftCommand;
    private ICommand rightCommand;
    private Object leftCommandParameter;
    private Object rightCommandParameter;

    //endregion

    //region Constructor

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    //endregion

    //Init Methods

    private void init(Context context, @Nullable AttributeSet attrs){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.default_title_bar, this, true);

        initView();
        initComponents();

    }

    private void initView(){
        title_left_icon = findViewById(R.id.title_left_icon_id);
        title_right_icon = findViewById(R.id.title_right_icon_id);
        title_name = findViewById(R.id.title_name_id);
    }

    private void initComponents(){
        if(leftIcon != null){
        }
        if(rightIcon != null){


        }
    }

    //endregion

    //region Property Setter

    public void setName(String name){
        this.name = name;
        title_name.setText(name);
    }

    public void setLeftIcon(Drawable leftIcon){
        title_left_icon.setImageDrawable(leftIcon);
        title_left_icon.setVisibility(VISIBLE);
    }

    public void setRightIcon(Drawable rightIcon){
        title_right_icon.setImageDrawable(rightIcon);
        title_right_icon.setVisibility(VISIBLE);
    }

    public void setLeftCommand(ICommand leftCommand){
        title_left_icon.setOnClickListener(v -> {
            if(leftCommand != null){
                leftCommand.execute(leftCommandParameter);
            }
        });
    }

    public void setRightCommand(ICommand rightCommand){
        title_right_icon.setOnClickListener(v -> {
            if(rightCommand != null){
                rightCommand.execute(rightCommandParameter);
            }
        });
    }

    public void setLeftCommandParameter(Object leftCommandParameter){
        this.leftCommandParameter = leftCommandParameter;
    }

    public void setRightCommandParameter(Object rightCommandParameter){
        this.rightCommandParameter = rightCommandParameter;
    }

    //endregion
}
