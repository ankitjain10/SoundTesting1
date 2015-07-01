package com.example.dell.soundtesting;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class Gun extends ActionBarActivity implements View.OnClickListener{
    Button btnpistol,btnmachinegun,btnshotgun,btnallround,btnsniper,btnshotgunfull;
    SoundPool sp;
    Bitmap bmp,bmp1;
    ImageView imagePiston;
    int  pistol,machinegun,shotgun,allround,sniper,shotgunfull,reload,counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gun);

        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.shootsa);
        bmp1= BitmapFactory.decodeResource(getResources(),R.drawable.shootsg);

        btnpistol=(Button)findViewById(R.id.buttonPistol);
        btnmachinegun=(Button)findViewById(R.id.buttonMachine);
        btnshotgun=(Button)findViewById(R.id.buttonShotgun);
        btnallround=(Button)findViewById(R.id.buttonAlrounds);
        btnsniper=(Button)findViewById(R.id.buttonSniper);
        btnshotgunfull=(Button)findViewById(R.id.buttonShotgunfull);
        imagePiston=(ImageView)findViewById(R.id.imagePiston);

        pistol=machinegun=shotgun=allround=sniper=shotgunfull=reload=counter=0;
        sp=new SoundPool(5, AudioManager.STREAM_MUSIC,0);

        reload=sp.load(this,R.drawable.reload,1);
        pistol=sp.load(this,R.drawable.pistol,1);
        machinegun=sp.load(this,R.drawable.machinegun,1);
        shotgun=sp.load(this,R.drawable.shotgun,1);
        allround=sp.load(this,R.drawable.allround,1);
        sniper=sp.load(this,R.drawable.sniper,1);
        shotgunfull=sp.load(this,R.drawable.shotgunfire,1);

        btnpistol.setOnClickListener(this);
        btnmachinegun.setOnClickListener(this);
        btnshotgun.setOnClickListener(this);
        btnallround.setOnClickListener(this);
        btnsniper.setOnClickListener(this);
        btnshotgunfull.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonPistol:{
                counter++;
                if(counter==7){
                    if(reload!=0)
                        sp.play(reload,1,1,0,0,1);
                        pistol=0;
                    counter=0;
                }
                if(pistol!=0)
                    sp.play(pistol,1,1,0,0,1);
                pistol=sp.load(this,R.drawable.pistol,1);
                imagePiston.setImageBitmap(bmp);
            }
//            imagePiston.setImageBitmap(bmp1);
            break;
            case R.id.buttonMachine:{
                if(machinegun!=0)
                    sp.play(machinegun,1,1,0,0,1);
            }
            break;
            case R.id.buttonShotgun:{
                if(shotgun!=0)
                    sp.play(shotgun,1,1,0,0,1);
            }
            break;
            case R.id.buttonAlrounds:{
                if(allround!=0)
                    sp.play(allround,1,1,0,0,1);
            }
            break;
            case R.id.buttonSniper:{
                if(sniper!=0)
                    sp.play(sniper,1,1,0,0,1);
            }
            break;
            case R.id.buttonShotgunfull:{
                if(shotgunfull!=0)
                    sp.play(shotgunfull,1,1,0,0,1);
            }
            break;
        }
    }
}
