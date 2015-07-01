package com.example.dell.soundtesting;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by dell on 6/17/2015.
 */
public class Menu extends ListActivity {
    String[] classes={"Gun","Slider","Tabs","WebBrowser","SharedPrefs","InternalStorage","ExternalStorage"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,classes));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String pos=classes[position];

        try {
            Class ourClass=Class.forName("com.example.dell.soundtesting."+pos);
            Intent intent=new Intent(Menu.this,ourClass);
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
