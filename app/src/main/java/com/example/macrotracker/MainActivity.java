package com.example.macrotracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private Button viewEntriesButton;
    private Button updateGoalsButton;
    private MyDBManager myDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDBManager = new MyDBManager(this);
        addButton = (Button) findViewById(R.id.main_add_button);
        viewEntriesButton = (Button) findViewById(R.id.main_journal_button);
        updateGoalsButton = (Button) findViewById(R.id.set_goal_button);
        addButton();
        viewEntriesButton();
        setGoalsButton();

    }

    public void addButton(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(), AddEntryScreen.class);
                startActivity(intent);
            }
        });
    }

    public void viewEntriesButton(){
        viewEntriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(),JournalScreen.class);
                startActivity(intent);
            }
        });
    }

    public void setGoalsButton(){
        updateGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(),SetGoalScreen.class);
                startActivity(intent);
            }
        });
    }
}
