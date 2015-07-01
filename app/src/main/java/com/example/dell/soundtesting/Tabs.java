package com.example.dell.soundtesting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by dell on 6/17/2015.
 */
public class Tabs extends Activity implements View.OnClickListener {
    Button button1,button2,button3;
    TextView tab2Text;
    TabHost th;
    long start ,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        th=(TabHost)findViewById(R.id.tabHost);
        th.setup();

        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        tab2Text=(TextView)findViewById(R.id.tab2Text);
        tab2Text.setVisibility(View.INVISIBLE);

        TabHost.TabSpec spec=th.newTabSpec("Tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Start/Stop");
        th.addTab(spec);

        spec=th.newTabSpec("Tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("TEXT TAB");
        th.addTab(spec);

        spec=th.newTabSpec("Tag3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Add a new Tab");
        th.addTab(spec);

        spec=th.newTabSpec("Tag4");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Tab 4");
        th.addTab(spec);

        spec=th.newTabSpec("Tag5");
        spec.setContent(R.id.tab5);
        spec.setIndicator("Tab 5");
        th.addTab(spec);
        start=0;
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.button1:
             start=System.currentTimeMillis();
             break;

         case R.id.button2:
             stop=System.currentTimeMillis();
             if(start!=0){
                 tab2Text.setVisibility(View.VISIBLE);
                 long result=((stop-start)/1000);
                 int millis= (int) result;
                 int secs= (int) millis/1000;
                 int mins= (int) secs/60;
                 millis=millis%100;
                 secs=secs%60;
                 tab2Text.setText(String.format("%d:%02d:%02d",mins,secs,millis));
             }
             start=0;
             break;

         case R.id.button3:
             TabHost.TabSpec newSpec=th.newTabSpec("Tag3");
             newSpec.setContent(new TabHost.TabContentFactory() {
                 @Override
                 public View createTabContent(String tag) {
                     TextView textViews=new TextView(Tabs.this);
                          textViews.setText("New TAB created!!");
                     return textViews;
                 }
             });
             newSpec.setIndicator("New Tab!");
             th.addTab(newSpec);
             break;
     }
    }
}
