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
        addGoal();
    }

    public void homeButton(){
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(SetGoalScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addGoal(){
        updateButton.setOnClickListener(new View.OnClickListener() {
            int pro;
            int car;
            int fat;
            @Override
            public void onClick(View v) {
                if(gPro.getText().toString().equals("")){
                    pro = 0;
                }else{
                    pro = Integer.parseInt(gPro.getText().toString());
                }

                if(gCar.getText().toString().equals("")){
                    car = 0;
                }else{
                    car = Integer.parseInt(gCar.getText().toString());
                }

                if(gFat.getText().toString().equals("")){
                    fat = 0;
                }else{
                    fat = Integer.parseInt(gFat.getText().toString());
                }
                Goals goal = new Goals(pro, car, fat);
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
