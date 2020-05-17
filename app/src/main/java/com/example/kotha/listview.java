package com.example.kotha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kotha.database.database;
import com.example.kotha.database.room_contract;

import java.util.ArrayList;


public class listview extends AppCompatActivity {
    database myDB;
    adapter adapter;
    EditText filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        filter = (EditText) findViewById(R.id.edit_query);
        myDB = new database(this);
        ArrayList<room> theList = new ArrayList<>();
        theList = myDB.getAlldata();
        ListView arr = (ListView) findViewById(R.id.listView);
        adapter = new adapter(this, theList);
        arr.setAdapter(adapter);
        arr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent vi = new Intent(listview.this, viewList.class);
                vi.putExtra(room_contract.Movie.COLUMN_NAME_ID, id);
                startActivity(vi);
            }
        });
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (listview.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter, menu);
        return true;
    }

}
