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
            String name;
            int pro;
            int car;
            int fat;
            @Override
            public void onClick(View v) {
                if(etFoodName.getText().toString().equals("")){
                    name = "Entry name not added";
                } else{
                    name = etFoodName.getText().toString();
                }

                if(etUserPro.getText().toString().equals("")) {
                    pro = 0;
                } else {
                    pro = Integer.parseInt(etUserPro.getText().toString());
                }

                if(etUserCarb.getText().toString().equals("")) {
                    car = 0;
                }else {
                    car = Integer.parseInt(etUserCarb.getText().toString());
                }

                if(etUserFat.getText().toString().equals("")) {
                    fat = 0;
                }else {
                    fat = Integer.parseInt(etUserFat.getText().toString());
                }

                Food food = new Food(name, pro, car, fat);
                boolean insertData = myDBManager.addFoodToDb(food);

                if(insertData == true){
                    Toast.makeText(AddEntryScreen.this, "Entry Added!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddEntryScreen.this, "There was an error!", Toast.LENGTH_SHORT).show();
                }
                etFoodName.setText("");
                etUserPro.setText("");
                etUserCarb.setText("");
                etUserFat.setText("");
            }
        });

    }

    public void viewHomeButton(){
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(AddEntryScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void switchToJournal(){
        viewEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(AddEntryScreen.this,JournalScreen.class);
                startActivity(intent);
            }
        });
    }
}
