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
    private TextView estimatedText;
    private MyDBManager myDBManager;
    private boolean keepEstimating = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry_screen);
        etFoodName = (EditText) findViewById(R.id.newFoodName);
        etUserCarb = (EditText) findViewById(R.id.newUserCarb);
        etUserPro = (EditText) findViewById(R.id.newUserPro);
        etUserFat = (EditText) findViewById(R.id.newUserFat);
        estimatedText = (TextView) findViewById(R.id.estimated_calorie_amount);
        addItemButton = (Button) findViewById(R.id.addNewItemButton);
        homeButton = (Button) findViewById(R.id.add_screen_home_button);
        viewEntryButton = (Button) findViewById(R.id.add_screen_journal_button);
        myDBManager = new MyDBManager(this);
        //estimatedText.setText(estimatedAmount(0, 0, 0));

        addFoodToLog();
        viewHomeButton();
        switchToJournal();
    }

    /*public String estimatedAmount(int x, int y, int z){
        if(x ==0){ x= 0;}
        else {
            x = Integer.parseInt(etUserPro.getText().toString());
        }
        if(y ==0){ y= 0;}
        else{
            y = Integer.parseInt(etUserCarb.getText().toString());
        }
        if(z ==0){ z= 0;}
        else {
            z = Integer.parseInt(etUserFat.getText().toString());
        }
        int amount = (x*4) + (y*4) + (z*9);
        String estAmount = Integer.toString(amount);
        return estAmount;
    }*/

    public void addFoodToLog(){
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food food = new Food(etFoodName.getText().toString(), Integer.parseInt(etUserPro.getText().toString()), Integer.parseInt(etUserCarb.getText().toString()), Integer.parseInt(etUserFat.getText().toString()));
                boolean insertData = myDBManager.addFoodToDb(food);

                if(insertData == true){
                    Toast.makeText(AddEntryScreen.this, "Entry Added!", Toast.LENGTH_SHORT).show();
                    //keepEstimating = false;
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
