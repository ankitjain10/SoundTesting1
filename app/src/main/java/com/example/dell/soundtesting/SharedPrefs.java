package com.example.dell.soundtesting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by dell on 6/18/2015.
 */
public class SharedPrefs extends Activity implements View.OnClickListener {

    Button buttonS,buttonL;
    EditText inputText,inputPass;
    TextView outText;
    public static String fileName="Some random file ";
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedprefs);
        init();
        sp=getSharedPreferences(fileName,0);

    }

    private void init() {
        buttonL=(Button)findViewById(R.id.buttonL);
        buttonS=(Button)findViewById(R.id.buttonS);
        inputText=(EditText)findViewById(R.id.inputext);
        inputPass=(EditText)findViewById(R.id.inputPass);
        outText=(TextView)findViewById(R.id.outText);
        buttonL.setOnClickListener(this);
        buttonS.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonL:
                SharedPreferences.Editor editor=sp.edit();
                editor.putString("input",inputText.getText().toString());
                editor.putString("pass",inputText.getText().toString());
                editor.apply();
                break;
            case R.id.buttonS:

                String stringText=sp.getString("input","");
                String stringPass=sp.getString("pass","");
                outText.setText(stringText);
                outText.setText(stringPass);
                break;
        }
    }
}
