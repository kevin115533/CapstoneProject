package com.example.macrotracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddEntryScreen extends MainActivity {
    EditText etFoodName;
    EditText etUserPro;
    EditText etUserCarb;
    EditText etUserFat;
    TextView foodList;
    Button addItemButton;
    MyDBManager myDBManager;

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
        myDBManager = new MyDBManager(this);

        addFoodToLog();
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
}
