package com.example.dell.soundtesting;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SlidingDrawer;

/**
 * Created by dell on 6/17/2015.
 */
public class Slider extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SlidingDrawer.OnDrawerOpenListener {
    Button buttonOpened,buttonClose,buttonToggle;
    CheckBox checkBox;
    SlidingDrawer slider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slider);
        buttonOpened=(Button)findViewById(R.id.buttonOpen);
        buttonClose=(Button)findViewById(R.id.buttonClose);
        buttonToggle=(Button)findViewById(R.id.buttonToggle);
        checkBox=(CheckBox)findViewById(R.id.checkboxSlider);
        slider=(SlidingDrawer)findViewById(R.id.slidingDrawer);
        slider.setOnDrawerOpenListener(this);


        buttonOpened.setOnClickListener(this);
        buttonClose.setOnClickListener(this);
        buttonToggle.setOnClickListener(this);

        checkBox.setOnCheckedChangeListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonOpen:
                slider.open();
                break;

            case R.id.buttonClose:
                slider.close();
                break;

            case R.id.buttonToggle:
                slider.toggle();
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    if(buttonView.isChecked()){
        slider.lock();
    }
        else
        slider.unlock();
    }

    @Override
    public void onDrawerOpened() {
        MediaPlayer mp=MediaPlayer.create(this,R.drawable.machinegun);
        mp.start();
    }
}
