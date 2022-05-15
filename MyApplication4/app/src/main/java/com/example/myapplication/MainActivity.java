package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txtMain;
    private Button btnPlus;
    private Button btnToast;
    private int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtMain = findViewById(R.id.txt_main);
        btnPlus = findViewById(R.id.btn_plus);
        btnToast = findViewById(R.id.btn_toast);

        btnPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                count++;
                txtMain.setText(count+"");
            }
        });

        btnToast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(MainActivity.this, txtMain.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }
}