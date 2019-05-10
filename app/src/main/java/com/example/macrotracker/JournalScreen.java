package com.example.macrotracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class JournalScreen extends MainActivity {
    private RecyclerView mRecyclerView;
    //private RecyclerView.Adapter mAdapter;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button jHomeButton;
    private Button addEntryButton;
    private EditText removeID;
    private Button removeButton;
    MyDBManager myHelper = new MyDBManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_screen);

        mRecyclerView = findViewById(R.id.foodEntryList);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(myHelper.getList());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        jHomeButton = (Button)findViewById(R.id.j_screen_home_button);
        addEntryButton = (Button)findViewById(R.id.j_screen_add_button);
        removeID = (EditText)findViewById(R.id.removeID);
        removeButton = (Button)findViewById(R.id.remove_button);

        viewHomeButton();
        switchToAddScreen();
        removeButton();
    }


    public void viewHomeButton(){
        jHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(JournalScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void switchToAddScreen(){
        addEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(JournalScreen.this,AddEntryScreen.class);
                startActivity(intent);
            }
        });
    }

    public void removeButton(){
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(removeID.getText().toString());
                myHelper.deleteRow(x);
                Toast.makeText(JournalScreen.this, "Item Removed!", Toast.LENGTH_SHORT).show();
                Intent intent;
                intent = new Intent(JournalScreen.this,JournalScreen.class);
                startActivity(intent);
            }
        });
    }


}
