package com.example.week3_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThridActivity extends AppCompatActivity {

    private Intent intent;

    private TextView txtmain;
    private TextView txtsub;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        txtsub = findViewById(R.id.txt_sub);
        txtmain = findViewById(R.id.txt_main);

        intent = getIntent();

        String mainText = intent.getStringExtra("maintxt");
        String subText = intent.getStringExtra("subtxt");

        txtmain.setText(mainText

        );
        txtsub.setText(subText

        );

    }
}
