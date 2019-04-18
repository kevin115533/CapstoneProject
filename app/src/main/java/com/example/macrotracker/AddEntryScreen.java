package com.example.macrotracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddEntryScreen extends AppCompatActivity {
    private EditText etFoodName;
    private EditText etUserPro;
    private EditText etUserCarb;
    private EditText etUserFat;
    private TextView foodList;
    private Button addItemButton;
    private Button homeButton;
    private Button viewEntryButton;
    private MyDBManager myDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry_screen);
        etFoodName = (EditText) findViewById(R.id.newFoodName);
        etUserCarb = (EditText) findViewById(R.id.newUserCarb);
        etUserPro = (EditText) findViewById(R.id.newUserPro);
        etUserFat = (EditText) findViewById(R.id.newUserFat);
        foodList = (TextView) findViewById(R.id.newFoodList);
        addItemButton = (Button) findViewById(R.id.addNewItemButton);
        homeButton = (Button) findViewById(R.id.add_screen_home_button);
        viewEntryButton = (Button) findViewById(R.id.add_screen_journal_button);
        myDBManager = new MyDBManager(this);

        addFoodToLog();
        viewHomeButton();
        switchToJournal();
    }

    public void addFoodToLog(){
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food food = new Food(etFoodName.getText().toString(), Integer.parseInt(etUserPro.getText().toString()), Integer.parseInt(etUserCarb.getText().toString()), Integer.parseInt(etUserFat.getText().toString()));
                boolean insertData = myDBManager.addFoodToDb(food);

                if(insertData == true){
                    Toast.makeText(AddEntryScreen.this, "Entry Added!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddEntryScreen.this, "There was an error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void viewHomeButton(){
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void switchToJournal(){
        viewEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(),JournalScreen.class);
                startActivity(intent);
            }
        });
    }
}
