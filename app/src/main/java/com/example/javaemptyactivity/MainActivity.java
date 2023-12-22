package com.example.javaemptyactivity;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javaemptyactivity.models.Agent;

public class MainActivity extends AppCompatActivity {
    Button btn_addAgent ;
    EditText et_agentName;
    EditText et_agentNumber;
    EditText et_agentDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_addAgent = findViewById(R.id.btn_addAgent);
        EditText et_agentName = findViewById(R.id.et_agentName);
        EditText et_agentNumber = findViewById(R.id.et_agentNumber);
        EditText et_agentDescription = findViewById(R.id.et_agentDescription);
        /**
         * Handle On Add Agent Event
         * with success message, and  error handling
         */
        btn_addAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = et_agentName.getText().toString();
                    String number = et_agentNumber.getText().toString();
                    String description = et_agentDescription.getText().toString();

                    Agent agent = new Agent(-1, name, number, description);
                    DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this);
                    boolean insertSuccessful = dbHelper.insertAgent(agent);

                    if (insertSuccessful) {
                        Toast.makeText(MainActivity.this, "Agent added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Failed to add agent", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }





}