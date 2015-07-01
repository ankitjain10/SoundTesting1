package com.example.dell.soundtesting;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class InternalStorage extends ActionBarActivity implements View.OnClickListener {

    Button buttonS,buttonL;
    EditText inputText,inputPass;
    TextView outText;
    public static String fileName="Some random file ";
    FileOutputStream fos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        init();

    }

    public void init() {
        buttonL=(Button)findViewById(R.id.buttonL);
        buttonS=(Button)findViewById(R.id.buttonS);
        inputText=(EditText)findViewById(R.id.inputext);
        inputPass=(EditText)findViewById(R.id.inputPass);
        outText=(TextView)findViewById(R.id.outText);
        buttonL.setOnClickListener(this);
        buttonS.setOnClickListener(this);

        try {
            fos =openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonL:
                String texts=inputText.getText().toString();
                try {
                    fos=openFileOutput(fileName,Context.MODE_PRIVATE);
                    fos.write(texts.getBytes());
                    fos.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.buttonS:
                new AsyncClass().execute(fileName);
                break;
                }
    }

    ProgressDialog progressDialog;


    private class AsyncClass extends AsyncTask<String ,Integer,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(InternalStorage.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            for(int i=0;i<20;i++){
                publishProgress(5);
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            progressDialog.dismiss();
            String collected = null;
            FileInputStream fis = null;
            try {
                fis = openFileInput(fileName);
                byte[] dataArray = new byte[fis.available()];
                while (fis.read(dataArray) != -1) {
                    collected = new String(dataArray);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            outText.setText(s);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.incrementProgressBy(values[0]);
        }
    }
}
