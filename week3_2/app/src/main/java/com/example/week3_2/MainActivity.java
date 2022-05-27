package com.example.week3_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity {


    private TextView txtMain;
    private Button btnMain1;
    private Button btnMain2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMain = findViewById(R.id.txt_main);
        btnMain1 = findViewById(R.id.btn_main1);
        btnMain2 = findViewById(R.id.btn_main2);

        btnMain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SubActivity.class);

                intent.putExtra("maintxt",txtMain.getText().toString());

                startActivity(intent);
            }
        });

        btnMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ThridActivity.class);

                intent.putExtra("maintxt",txtMain.getText().toString());

                startActivity(intent);
            }
        });


    }
}