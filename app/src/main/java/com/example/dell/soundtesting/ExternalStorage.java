package com.example.dell.soundtesting;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class ExternalStorage extends ActionBarActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    TextView tvRead,tvWrite;
    private static String state;
    Boolean canRead,canWrite;
    Spinner spinner;
    String[] paths={"Music","Pictures","Downloads"};
    File path=null;
    File file=null;
    Button bConfirm,bSave;
    EditText etSaveas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        intit();
    }

    private void intit() {
        bConfirm=(Button)findViewById(R.id.bConfirm);
        bSave=(Button)findViewById(R.id.bSave);
        etSaveas=(EditText)findViewById(R.id.etSaveas);
        bConfirm.setOnClickListener(this);
        bSave.setOnClickListener(this);

        tvRead=(TextView)findViewById(R.id.tvRead);
        tvWrite=(TextView)findViewById(R.id.tvWrite);
        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(ExternalStorage.this,android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        checkstate();

    }

    private void checkstate() {
        state= Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)){
            tvWrite.setText("true");
            tvRead.setText("true");
        }

        else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            tvWrite.setText("false");
            tvRead.setText("true");
        }
        else if (state.equals(Environment.MEDIA_UNMOUNTABLE)){
            tvWrite.setText("UNMOUNTABLE");
            tvRead.setText("UNMOUNTABLE");
        }
        else if (state.equals(Environment.MEDIA_UNKNOWN)){
            tvWrite.setText("UNKNOWN");
            tvRead.setText("UNKNOWN");
        }

        else if (state.equals(Environment.MEDIA_REMOVED)){
            tvWrite.setText("REMOVED");
            tvRead.setText("REMOVED");
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         int pos=spinner.getSelectedItemPosition();
        switch (pos){
            case 0:
                path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this,"please select an item",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bConfirm:
            bSave.setVisibility(View.VISIBLE);
                break;
            case R.id.bSave:
                String filePath=etSaveas.getText().toString();
                file=new File(path,filePath);
                checkstate();
                if(canWrite==canRead==true){
                    try {
                        InputStream is = getResources().openRawResource(R.drawable.shootsa);
                        OutputStream os= new FileOutputStream(file);
                        byte[] data=new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        is.close();
                        os.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                      catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }
}
