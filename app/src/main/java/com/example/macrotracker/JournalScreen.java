package com.example.macrotracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class JournalScreen extends MainActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button jHomeButton;
    private Button addEntryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_screen);

        MyDBManager myHelper = new MyDBManager(this);

        mRecyclerView = findViewById(R.id.foodEntryList);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(myHelper.getList());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        jHomeButton = (Button)findViewById(R.id.j_screen_home_button);
        addEntryButton = (Button)findViewById(R.id.j_screen_add_button);

        viewHomeButton();
        viewEntriesButton();
    }

    public void viewHomeButton(){
        jHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void viewEntriesButton(){
        addEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(),AddEntryScreen.class);
                startActivity(intent);
            }
        });
    }
}
