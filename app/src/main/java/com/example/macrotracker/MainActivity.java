package com.example.macrotracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private Button viewEntriesButton;
    private Button updateGoalsButton;
    private TextView proText;
    private TextView carText;
    private TextView fatText;
    private TextView calText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDBManager myDB = new MyDBManager(this);
        addButton = (Button) findViewById(R.id.main_add_button);
        viewEntriesButton = (Button) findViewById(R.id.main_journal_button);
        updateGoalsButton = (Button) findViewById(R.id.set_goal_button);
        proText = (TextView) findViewById(R.id.pro_consumed_goal);
        carText = (TextView) findViewById(R.id.car_consumed_goal);
        fatText = (TextView) findViewById(R.id.fat_consumed_goal);
        calText = (TextView) findViewById(R.id.cal_consumed_goal);

        proText.setText(Integer.toString(myDB.consumedGoals().getgPro()) + " / " + Integer.toString(myDB.setGoals().getgPro()));
        carText.setText(Integer.toString(myDB.consumedGoals().getgCar())+ " / " + Integer.toString(myDB.setGoals().getgCar()));
        fatText.setText(Integer.toString(myDB.consumedGoals().getgFat())+ " / " + Integer.toString(myDB.setGoals().getgFat()));
        calText.setText(Integer.toString(myDB.consumedGoals().getTotCal())+ " / " + Integer.toString(myDB.setGoals().getTotCal()));
        addButton();
        viewEntriesButton();
        setGoalsButton();
    }

    public void addButton(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, AddEntryScreen.class);
                startActivity(intent);
            }
        });
    }

    public void viewEntriesButton(){
        viewEntriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this,JournalScreen.class);
                startActivity(intent);
            }
        });
    }

    public void setGoalsButton(){
        updateGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this,SetGoalScreen.class);
                startActivity(intent);
            }
        });
    }
}
