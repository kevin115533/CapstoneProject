package com.example.macrotracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetGoalScreen extends AppCompatActivity {
    private Button homeButton;
    private Button updateButton;
    private EditText gPro;
    private EditText gCar;
    private EditText gFat;
    private MyDBManager myDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal_screen2);
        myDBManager = new MyDBManager(this);
        homeButton = (Button) findViewById(R.id.goal_screen_home_button);
        updateButton = (Button) findViewById(R.id.update_goal_button);
        gPro = (EditText) findViewById(R.id.goalPro);
        gCar = (EditText) findViewById(R.id.goalCar);
        gFat = (EditText) findViewById(R.id.goalFat);

        homeButton();
        updateButton();
    }

    public void homeButton(){
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addFoodToLog(){
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Goals goal = new Goals(Integer.parseInt(gPro.getText().toString()),Integer.parseInt(gCar.getText().toString()),Integer.parseInt(gFat.getText().toString()));
                boolean addGoal = myDBManager.addGoalsToDB(goal);

                if(addGoal == true){
                    Toast.makeText(SetGoalScreen.this, "Goals Updated!", Toast.LENGTH_SHORT).show();
                    //keepEstimating = false;
                }else{
                    Toast.makeText(SetGoalScreen.this, "There was an error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
